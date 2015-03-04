package fr.xebia.java8.refactoring.step5;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MerchantTest {

    @Test
    public void should_execute_future_for_products_and_stocks() throws Exception {
        Merchant merchant = new CompletableFutureUtil().executeMerchant();

        assertThat(merchant.getProducts().get(0).getName()).isEqualTo("name1");
        assertThat(merchant.getStocks().get(0)).isEqualTo(100);
    }

    @Test
    public void should_execute_async_future_for_products_and_stocks() throws Exception {
        Merchant merchant = new CompletableFutureUtil().executeMerchantAsync();

        assertThat(merchant.getProducts()).isNotNull();
        assertThat(merchant.getProducts().get(0).getName()).isEqualTo("name1");
        assertThat(merchant.getStocks().get(0)).isEqualTo(100);
    }
}