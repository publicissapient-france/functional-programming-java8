package fr.xebia.java8.refactoring.step5.search;

import fr.xebia.java8.refactoring.step5.Product;
import fr.xebia.java8.refactoring.step5.repository.FakeRepositoryTools;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchEngine {

    private final int nodeId;

    public SearchEngine(int nodeId) {

        this.nodeId = nodeId;
    }

    public static SearchEngine getNode(int nodeId) {
        return new SearchEngine(nodeId);
    }

    public Product searchByName(String name) {
        FakeRepositoryTools.waitInSecond(1.5);
        //return new Product(1, name, name + " nodeId:"+nodeId, Product.Category.BOOKS, stockId);
        return null;
    }

    public static List<SearchEngine> getNodes() {
        return IntStream.range(0, 10)
                .mapToObj(i -> new SearchEngine(i))
                .collect(Collectors.toList());
    }
}
