package org.relaxcg.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.KnnQuery;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.util.ObjectBuilder;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * @author relaxcg
 * @date 2024/3/19 16:52
 */
@Component
public class ESUtils {

    @Resource
    private ElasticsearchClient elasticsearchClient;

    public void createIndex(String name) {

    }

    public void createIndex(String name, Function<TypeMapping.Builder, ObjectBuilder<TypeMapping>> mappingFun) throws IOException {
        elasticsearchClient.indices()
                .create(c -> c.index(name)
                        .mappings(mappingFun));
    }

    public void createByJson(String name, String id, String jsonContent) {

    }


    @Resource
    private ObjectMapper objectMapper;

    @Data
    @NoArgsConstructor
    static class Feature {
        @JsonAlias("embedding_id")
        private String embeddingId;
        @JsonAlias("food_id")
        private String foodId;
    }

    public void query() throws IOException {
        List<Float> list = new ArrayList<>(floats.length);
        for (float aFloat : floats) {
            list.add(aFloat);
        }
        KnnQuery knnQuery = KnnQuery.of(fn -> fn.field("embedding")
                .queryVector(list)
                .k(3)
                .numCandidates(10000));


        SearchResponse<Feature> search = elasticsearchClient.search(
                s -> s.index("wkt_food_features_20240319_v1")
                        .knn(knnQuery)
                        .source(fn->fn.filter(v->v.includes("embedding_id", "food_id"))),
                Feature.class);

        List<Feature> features = new ArrayList<>();
        for (Hit<Feature> hit : search.hits().hits()) {
            features.add(hit.source());
        }

        System.out.println(objectMapper.writeValueAsString(features));

    }

    private float[] floats = new float[]{
            -1.359803557395935f,
            -0.6732935905456543f,
            0.504629373550415f,
            -1.0405398607254028f,
            0.7474962472915649f,
            -0.07763105630874634f,
            1.639236330986023f,
            0.6268578171730042f,
            2.1629908084869385f,
            -1.0475200414657593f,
            0.7136850953102112f,
            0.346596360206604f,
            -2.15786075592041f,
            0.27174100279808044f,
            0.2766290009021759f,
            -0.13259360194206238f,
            -0.15609842538833618f,
            0.6757497787475586f,
            -2.0053598880767822f,
            -0.5325530767440796f,
            0.5176844596862793f,
            -1.2797222137451172f,
            -0.5756082534790039f,
            0.25750648975372314f,
            0.9427595138549805f,
            -0.27287501096725464f,
            -0.4336065948009491f,
            0.6421031355857849f,
            -0.5086659789085388f,
            0.7505624294281006f,
            0.1814907342195511f,
            1.002484917640686f,
            -0.36971527338027954f,
            1.537840723991394f,
            -1.5069037675857544f,
            0.41024908423423767f,
            -0.11921851336956024f,
            0.08211676776409149f,
            0.28157758712768555f,
            0.3451043963432312f,
            -0.21342535316944122f,
            -2.042809009552002f,
            1.0765647888183594f,
            0.13337714970111847f,
            -0.4256209135055542f,
            2.306793451309204f,
            0.3521156311035156f,
            0.09039423614740372f,
            1.8617452383041382f,
            -0.1323692500591278f,
            -0.0934116467833519f,
            0.334460586309433f,
            0.47826918959617615f,
            -0.5029724836349487f,
            1.0847387313842773f,
            1.1878091096878052f,
            0.34534603357315063f,
            -0.7504906058311462f,
            0.700751543045044f,
            0.9995939135551453f,
            -1.1213798522949219f,
            1.2955085039138794f,
            -1.7831482887268066f,
            0.29334497451782227f,
            0.2196548879146576f,
            0.17933692038059235f,
            -0.5262836217880249f,
            0.4370254874229431f,
            0.7436301708221436f,
            0.1414915919303894f,
            0.08454740047454834f,
            1.1527665853500366f,
            -0.26335588097572327f,
            -0.6095383763313293f,
            -0.9042357206344604f,
            -0.11359511315822601f,
            -0.729522705078125f,
            -1.08124840259552f,
            -1.567818284034729f,
            0.37232545018196106f,
            -0.2977226674556732f,
            0.9455320239067078f,
            -0.4266010522842407f,
            0.8260347843170166f,
            -0.13391901552677155f,
            0.5973886251449585f,
            -0.2846548855304718f,
            -0.6611586809158325f,
            -1.047743558883667f,
            -0.9736776947975159f,
            1.635669231414795f,
            0.529927134513855f,
            0.6638845205307007f,
            -0.7513424158096313f,
            0.7249861359596252f,
            -0.03956017643213272f,
            1.2372791767120361f,
            0.7706698775291443f,
            -0.12663133442401886f,
            -0.3232802450656891f,
            -1.2138631343841553f,
            -0.5203784108161926f,
            0.3536298871040344f,
            -1.2462244033813477f,
            -0.054942402988672256f,
            0.45135393738746643f,
            -0.8553171753883362f,
            -0.11674938350915909f,
            0.09378588199615479f,
            0.876400351524353f,
            -1.0790719985961914f,
            1.1552075147628784f,
            0.24118468165397644f,
            -0.2670702338218689f,
            0.508604109287262f,
            1.821786880493164f,
            -0.23974059522151947f,
            0.01223013922572136f,
            -0.5740759968757629f,
            1.1971276998519897f,
            -2.569370985031128f,
            -0.39574092626571655f,
            1.171319603919983f,
            -1.0486303567886353f,
            -0.5573564767837524f,
            -0.44815775752067566f,
            0.3307015299797058f,
            -0.6756735444068909f,
            -0.7270190715789795f,
            0.42695555090904236f,
            -0.9931663274765015f,
            0.08536797761917114f,
            -1.7834361791610718f,
            -0.04724178835749626f,
            0.6860605478286743f,
            1.2978192567825317f,
            0.9116177558898926f,
            -0.7616057991981506f,
            0.4336931109428406f,
            -1.6853001117706299f,
            0.9813588857650757f,
            0.16013194620609283f,
            1.0006358623504639f,
            -0.3635222911834717f,
            -0.8559165000915527f,
            -0.9304733872413635f,
            0.3241007626056671f,
            -0.2509746551513672f,
            0.16792815923690796f,
            0.8464717864990234f,
            -0.7393004894256592f,
            -0.7618374228477478f,
            0.1913568079471588f,
            0.6131533980369568f,
            0.1949118673801422f,
            -1.442979335784912f,
            -0.8784803748130798f,
            0.8470525741577148f,
            0.44958174228668213f,
            -1.0381379127502441f,
            1.3116017580032349f,
            0.2521471679210663f,
            -0.3994556963443756f,
            0.6785172820091248f,
            0.7447280287742615f,
            -0.5834423303604126f,
            -0.45872774720191956f,
            0.37886670231819153f,
            -0.04720558226108551f,
            -0.6288238167762756f,
            -1.40235435962677f,
            0.19511647522449493f,
            0.843323826789856f,
            0.08875463157892227f,
            0.3173791468143463f,
            0.5106171369552612f,
            -1.4737869501113892f,
            -0.7356166243553162f,
            1.2134783267974854f,
            -1.1401512622833252f,
            0.377637654542923f,
            -0.28801029920578003f,
            0.006849031895399094f,
            0.2815750539302826f,
            -1.7068229913711548f,
            1.5403447151184082f,
            0.39846912026405334f,
            1.1045236587524414f,
            0.4686417281627655f,
            0.808376669883728f,
            1.4436157941818237f,
            -1.1367799043655396f,
            -0.08751928061246872f,
            -0.7346614599227905f,
            -0.06434573978185654f,
            -0.06734800338745117f,
            -0.1272239089012146f,
            -1.1652320623397827f,
            0.7915446758270264f,
            -0.11078494042158127f,
            0.7355814576148987f,
            -1.1686009168624878f,
            -1.1261483430862427f,
            1.421433925628662f,
            -0.24488113820552826f,
            -0.30170080065727234f,
            -0.7848366498947144f,
            -0.8643432259559631f,
            -0.6671530604362488f,
            0.0223236121237278f,
            0.863244891166687f,
            -0.6337354779243469f,
            0.9022485017776489f,
            -0.029099950566887856f,
            -0.4335704743862152f,
            -0.5170527100563049f,
            0.8541584610939026f,
            -0.386351078748703f,
            -0.8512818813323975f,
            -0.38057976961135864f,
            0.5015042424201965f,
            -0.3238269090652466f,
            1.3039840459823608f,
            0.26476728916168213f,
            -0.010331491008400917f,
            -0.8048514723777771f,
            -1.0461065769195557f,
            1.5689830780029297f,
            -1.792822003364563f,
            -0.040687497705221176f,
            0.14542050659656525f,
            0.2442787140607834f,
            0.6386940479278564f,
            0.8947057127952576f,
            0.125165194272995f,
            -0.7105732560157776f,
            -1.3733657598495483f,
            2.207263231277466f,
            0.26486700773239136f,
            0.9505987763404846f,
            1.5638821125030518f,
            0.6092071533203125f,
            1.1552226543426514f,
            1.8570293188095093f,
            1.4745163917541504f,
            0.6719207167625427f,
            1.8568741083145142f,
            -0.3766588866710663f,
            1.416669249534607f,
            0.7306183576583862f,
            0.31810203194618225f,
            -0.01534472405910492f,
            -0.31737369298934937f,
            0.38474535942077637f,
            0.40139904618263245f,
            -0.9833990931510925f
    };

}
