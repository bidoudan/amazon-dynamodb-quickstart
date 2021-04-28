package org.acme.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FruitSyncService extends AbstractService{

    @Inject
    DynamoDbClient dynamoDb;

    public List<Fruit> findAll() {
        return dynamoDb.scanPaginator(scanRequest()).items().stream()
                .map(Fruit::from)
                .collect(Collectors.toList());
    }

    public List<Fruit> add(Fruit fruit) {
        dynamoDb.putItem(putRequest(fruit));
        return findAll();
    }

    public Fruit get(String name) {
        return Fruit.from(dynamoDb.getItem(getRequest(name)).item());
    }
}
