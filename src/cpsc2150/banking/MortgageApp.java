package cpsc2150.banking;

import cpsc2150.banking.controllers.IMortgageController;
import cpsc2150.banking.controllers.MortgageController;
import cpsc2150.banking.views.IMortgageView;
import cpsc2150.banking.views.MortgageView;

public class MortgageApp {

    public static void main(String[] args) {
        IMortgageView view = new MortgageView();
        IMortgageController controller = new MortgageController(view);
        view.setController(controller);
        controller.submitApplication();
    }
}