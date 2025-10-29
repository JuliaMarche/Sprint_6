import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {
    @ParameterizedTest
    @CsvSource({
            "header, Иван, Иванов, 'Москва, Кутузовский проспект 32', Лубянка, +79048889933, 01.12.2025, сутки, чёрный жемчуг, Комментарий один",
            "middle, Ольга, Иванова, Светлановский 67 , Спортивная, 89112223344, 19.10.2025, двое суток, серая безысходность, Комментарий 2"
    })

    void orderTest(String buttonPosition,
                   String name,
                   String lastName,
                   String address,
                   String metro,
                   String phone,
                   String date,
                   String rentPeriod,
                   String colour,
                   String comment) {

        if (buttonPosition.equals("header")) {
            mainPage.orderButtonHeaderClick();
        } else if (buttonPosition.equals("middle")) {
            mainPage.scrollToButtonMiddle();
            mainPage.orderButtonMiddleClick();
        }

        orderPage.fillOrderPage(name, lastName, address, metro, phone);
        rentPage.fillRentPage(date, rentPeriod, colour, comment);

        String confirmationText = rentPage.getConfirmationText();
        assertTrue(confirmationText.contains("Заказ оформлен"), "Заказ не оформлен");
    }
}
