package org.relaxcg.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author relaxcg
 * @date 2024/3/19 16:43
 */
@Configuration
public class ESClientConfig {

    @Value("${es.hosts}")
    private String hosts;
    @Value("${es.user}")
    private String user;
    @Value("${es.password}")
    private String password;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(user, password)
        );

        HttpHost[] httpHosts = httpHosts();

        RestClient restClient = RestClient
                .builder(httpHosts)
                // .builder(HttpHost.create("http://es-test01.yingzi.com:9208"))
                .setHttpClientConfigCallback(hc -> hc.setDefaultCredentialsProvider(credsProv))
                .build();
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

    private HttpHost[] httpHosts() {
        Assert.hasLength(hosts, "es.hosts must not be null");
        String[] hostArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        HttpHost httpHost;
        int index = 0;
        for (String host : hostArray) {
            String[] ipPort = host.split(":");
            Assert.isTrue(ipPort.length == 2, "es.hosts must be ip:port");
            httpHost = new HttpHost(ipPort[0], Integer.parseInt(ipPort[1]), "http");
            httpHosts[index++] = httpHost;
        }
        return httpHosts;
    }

}
