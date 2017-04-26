package database.documentdb;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

public class DocumentClientFactory {

    private static final String HOST = "https://product-test.documents.azure.cn:443/";

    private static final String MASTER_KEY = "59R736Pz37uYptz13FvKTKYTfuzGNpIZy1s0k8ILifK1WkmOIrsaPxqr99dwoJa5extTfsGktbR2BG25sdzZgg==";

    private static DocumentClient documentClient;

    public static DocumentClient getDocumentClient() {
        if (documentClient == null) {
            documentClient = new DocumentClient(HOST, MASTER_KEY, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }

        return documentClient;
    }

}
