import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasItems;

public class getApiAddressTestYM {
    private String api_key = "";
    private String coordinates = "39.744760,47.228568";
    private String url = "https://geocode-maps.yandex.ru/1.x/?apikey="+api_key+"&geocode="+coordinates+"&format=json";
    private String city = "Ростов-на-Дону";
    private String street = "Театральная площадь";
    private String house = "1";

    @Test
    void restTest(){

        String getText = RestAssured
                .given().log().all()
                .when()
                    .contentType(ContentType.JSON)
                    .get(url)
                .then().log().all()
                    .statusCode(200)
                    .body("response.GeoObjectCollection.featureMember[0].GeoObject.metaDataProperty.GeocoderMetaData.Address.Components.name",
                            hasItems(city,street,house))
                    .extract().response().jsonPath().getString("response.GeoObjectCollection.featureMember[0].GeoObject.metaDataProperty.GeocoderMetaData.text");

        System.out.println("Address in the response: " + getText);
        System.out.println("Test ok");

    }
}
