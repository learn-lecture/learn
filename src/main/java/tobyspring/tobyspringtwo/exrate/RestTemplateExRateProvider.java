package tobyspring.tobyspringtwo.exrate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import tobyspring.tobyspringtwo.payment.ExRateProvider;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RestTemplateExRateProvider implements ExRateProvider {

    private final RestTemplate restTemplate;

    @Override
    public BigDecimal getExRate(String currency) {
        final String url = "https://open.er-api.com/v6/latest/" + currency;

        return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
    }

}
