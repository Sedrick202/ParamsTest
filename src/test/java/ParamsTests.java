import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParamsTests {

    static Stream<Arguments>checkAboutCompany(){
        return Stream.of(
                Arguments.of("О нас",List.of("Вконтакте"),
                Arguments.of("Реквизиты"),List.of("Facebook"),
                Arguments.of("Пресс-центр"),List.of("Twitter"),
                Arguments.of("Контакты"),List.of("Одноклассники"),
                Arguments.of("Вакансии"),List.of("YouTube"),
                Arguments.of("Bug Bounty"),List.of("Instagram")
                ));


    }
    @MethodSource
    @DisplayName("Отображения списка о компании и соц сетей")
    @Owner("Eltsov Nikita")
    @Tag("Normal")
    @ParameterizedTest
    void checkAboutCompany(String searchQuery, List<String> expectedresult){
            open("https://www.wildberries.ru/catalog/obuv");
            $$("li.footer__item").find(Condition.text(searchQuery));
                   $$("li.footer__item").find(Condition.text(expectedresult.get(0)))
                           .shouldBe(Condition.visible);


    }


    @DisplayName("Проверка сайдбара обуви на wildberries")
    @Owner("Eltsov Nikita")
    @Tag("major")
    @ValueSource(strings = {"Детская",
            "Для новорожденных",
            "Женская",
            "Мужская",
            "Ортопедическая обувь",
            "Аксессуары для обуви"
    })
    @ParameterizedTest
    void checkCategory(String searchQuery) {
        open("https://www.wildberries.ru/catalog/obuv");
        $$("a.j-menu-item").find(Condition.text(searchQuery))
                .shouldBe(Condition.visible);

    }
    @DisplayName("Отображение товаров в категориях обвуви")
    @Owner("Eltsov Nikita")
    @Tag("Normal")
    @CsvSource(value = {
            "Детская| Ботинки",
            "Для новорожденных| Ботинки"

    },
            delimiter = '|')
    @ParameterizedTest
    void testCategoryContent(String searchQuery, String product) {
        open("https://www.wildberries.ru/catalog/obuv");
        $$("a.j-menu-item")
                .find(Condition.text(searchQuery))
                .click();
        $(byText(product)).shouldBe(Condition.visible);

    }
}




