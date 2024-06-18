package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
@Data
public class ProfilePage {
    private final SelenideElement reactTable = $(".ReactTable"),
            deleteButton = $("#delete-record-undefined"),
            closeSmallModalOk = $("#closeSmallModal-ok"),
            userName = $("#userName-value");
}
