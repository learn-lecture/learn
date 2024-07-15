package tobyspring.tobyspringtwo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

@Setter
public class ApiTemplate {

    private ApiExecutor apiExecutor;
    private ExRateExtractor exRateExtractor;

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ErApiExRateExtractor();
    }

    public BigDecimal getExRate(final String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(
            final String url,
            final ApiExecutor apiExecutor,
            final ExRateExtractor exRateExtractor
    ) {
        final URI uri;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final HttpURLConnection connection;
        final String response;

        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
