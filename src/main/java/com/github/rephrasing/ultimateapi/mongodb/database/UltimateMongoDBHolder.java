package com.github.rephrasing.ultimateapi.mongodb.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.Validate;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public abstract class UltimateMongoDBHolder {

    private MongoClient client;
    private CodecRegistry[] codecRegistries;

    /**
     * This method is used to add codec registries (MUST BE CALLED BEFORE {@link UltimateMongoDBHolder#connect(String)})
     * @param registries the registries
     */
    public void addCodecs(CodecRegistry... registries) {
        codecRegistries = registries;
    }

    public void connect(String connectionUri) {
        Validate.isTrue(client == null, "Attempted connection to MongoDB but found an existing connection");

        MongoClientSettings.Builder builder = MongoClientSettings.builder();

        CodecRegistry defaultRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        builder.codecRegistry(defaultRegistry);
        builder.applyConnectionString(new ConnectionString(connectionUri));

        if (codecRegistries != null) {
            for (CodecRegistry registry : codecRegistries) {
                builder.codecRegistry(registry);
            }
        }
        client = MongoClients.create(builder.build());
    }

    public void disconnect() {
        if (client == null) throw new IllegalArgumentException("Attempted to disconnect from MongoDB but did not find a connection");
        client.close();
    }

    public MongoClient getMongoClient() {
        if (client == null) throw new IllegalArgumentException("Attempted to retrieve MongoClient but did not find a connection");
        return client;
    }

    public MongoDatabase getMongoDatabase(String name) {
        if (client == null) throw new IllegalArgumentException("Attempted to retrieve MongoDatabase but did not find a connection");
        return client.getDatabase(name);
    }

    public MongoCollection<Document> getDocumentMongoCollection(String databaseName, String collectionName) {
        if (client == null) throw new IllegalArgumentException("Attempted to retrieve MongoCollection but did not find a connection");
        return client.getDatabase(databaseName).getCollection(collectionName);
    }

}
