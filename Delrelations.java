import java.io.*;
import java.util.*;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.cypher.javacompat.CypherParser;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.driver.v1.*;
 
public class Delrelations {
 
         public static void main(String[] args) throws IOException {
 
                 if (args.length != 1) {
                          System.out.println("Syntax error: Delrelations <sub_groupID>");
                          System.exit(1);
                 }
                Record record;
                 String query_string="";
                 String ID=args[0];
                 StatementResult result;
                 Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4juser", "xxxxxx" ) );
                 Session session = driver.session();
                int SIZEUOW = 500; 
 
                 long deleteCount = 0;
               
                 do {
                         
                           try  {
                            query_string="MATCH (a:GRAPHDB"+ID+")-[r]-(b:GRAPHDB"+ID+") WITH r LIMIT " + SIZEUOW + " DELETE r RETURN count(r) as counter";
                            result = session.run(query_string);
                            while ( result.hasNext() )
                             {
                             record = result.next();
                             deleteCount=record.get( "counter" ).asInt();
                           }
                            
                            } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                           } finally {
                            
                           System.out.println("Deleted relationships :"+deleteCount);
                     }
                 } while (deleteCount > 0);
                 session.close();
                 driver.close();
         }
}