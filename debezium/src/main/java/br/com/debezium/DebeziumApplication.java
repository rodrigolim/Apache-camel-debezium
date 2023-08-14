package br.com.debezium;

import java.io.IOException;
import java.util.Map;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.endpoint.dsl.InfinispanRemoteEndpointBuilderFactory.InfinispanOperation;
import org.apache.camel.component.debezium.DebeziumConstants;
import org.apache.kafka.connect.data.Struct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope;


@SpringBootApplication
public class DebeziumApplication {

    @Autowired
    private RoutingKafkaTemplate kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DebeziumApplication.class, args);
    }

    @Component
    class DebeziumRoute extends RouteBuilder {
        
        // convert map to JSON String
        public String mapToJson(Map value) {
            ObjectMapper mapperObj = new ObjectMapper();
            try {
                String jsonResp = mapperObj.writeValueAsString(value);
                return jsonResp;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;   
        }

        @Override
        public void configure() throws Exception {
            
//            final Predicate isPersonEvent =
//                    header(DebeziumConstants.HEADER_IDENTIFIER).endsWith(".person");
            
            final Predicate isCreateOrUpdateEvent =
                    header(DebeziumConstants.HEADER_OPERATION).in(
                            constant(Envelope.Operation.READ.code()),
                            constant(Envelope.Operation.CREATE.code()),
                            constant(Envelope.Operation.UPDATE.code()));
            
            
            from("debezium-postgres:myConnector?"
                    + "databaseHostname={{database.hostname}}"
                    + "&databasePort={{database.port}}"
                    + "&databaseUser={{database.user}}"
                    + "&databasePassword={{database.password}}"
                    + "&databaseDbname={{database.dbname}}"
                    + "&databaseServerName={{database.hostname}}"
                    + "&pluginName=pgoutput"
                    + "&snapshotMode=never"
                    + "&snapshotFetchSize=0"
                    + "&offsetStorageTopic=debeziumRouteTopic"
                    + "&offsetStorage=org.apache.kafka.connect.storage.MemoryOffsetBackingStore")
//                    + "&offsetStorage=org.apache.kafka.connect.storage.FileOffsetBackingStore"
//                    + "&offsetStorageFileName=/tmp/offset2.dat")
                   .routeId("debeziumRoute")
                   .setHeader("eventPayload", simple("${body}"))
                   .setBody(simple("${header.eventPayload}"))
                   .log("EVENTO: ${body}")
                   .log("    HEADERS: ${headers}")
                   .log("    metadata => ${headers.CamelDebeziumSourceMetadata}")
                   .log("    operation => '${headers.CamelDebeziumOperation}'")
                   .log("    database => '${headers.CamelDebeziumSourceMetadata[db]}' ")
                   .log("    table => '${headers.CamelDebeziumSourceMetadata[table]}'")
                   .log("    key => ${headers.CamelDebeziumKey}")
                      

                   .process(exchange -> {
                               Struct bodyStruct = exchange.getIn().getBody(Struct.class);   
                               Map bodyMap = exchange.getMessage().getBody(Map.class);
                               Map headersMap = exchange.getMessage().getHeaders();
                               Map metadata = (Map) headersMap.get("CamelDebeziumSourceMetadata");
                                  
                               
                             //Formatando o valor da chave primaria da tabela que gerou o evento de LOG
                               Struct camelDebeziumKey = (Struct) headersMap.get("CamelDebeziumKey");
                               Long idTabelaLog = (Long) camelDebeziumKey.get(camelDebeziumKey.schema().fields().get(0));  
                               
//                               String jsonAfter = mapToJson(bodyMap);
//                               
//                               System.out.println(bodyMap);
//                               System.out.println(jsonAfter);   
                               
        //                       kafkaTemplate.send("topic-1", bodyStruct.toString());
                               
                               
                               
                               
                           });
        }

    }

}
