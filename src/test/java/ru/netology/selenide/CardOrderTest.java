package ru.netology.selenide;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {


    @Test
    void ValidRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Петр Бенедиктович");
        form.$("[data-test-id='phone'] input").setValue("+79999999999");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void ifNameIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("");
        form.$("[data-test-id='phone'] input").setValue("+79999999999");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=name] .input__sub").shouldNotHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время"));
    }

    @Test
    void ifNumberLessTen() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Василий Хель");
        form.$("[data-test-id='phone'] input").setValue("+79999999");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=name] .input__sub").shouldNotHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время"));

    }

    @Test
    void ifNumberMoreEleven() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Борис Бритва");
        form.$("[data-test-id='phone'] input").setValue("+7999999999999999");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=name] .input__sub").shouldNotHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время"));

    }

    @Test
    void ifNumberIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Федор Паль");
        form.$("[data-test-id='phone'] input").setValue("");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=name] .input__sub").shouldNotHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время"));

    }
}