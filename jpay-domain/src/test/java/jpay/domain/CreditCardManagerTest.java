package jpay.domain;

import jpay.model.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Collection;

/**
 * CreditCardManagerTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/jpay-domain-test-context.xml"})
public class CreditCardManagerTest
{
    @Inject
    CreditCardManager creditCardManager;

    @Test
    public void testAddCreditCard ()
    {
        String n1 = "number3";
        CreditCard c1 = new CreditCard("name1", n1);
        creditCardManager.addCreditCard(c1);

        CreditCard c2 = creditCardManager.getCreditCardByNumber(n1);
        assert n1.equals(c2.getNumber()) : String.format("Expected %s but got %s", n1, c2.getNumber());

    }

    @Test
    public void testGetCreditCardByName ()
    {
        String holder = "name1";
        CreditCard c1 = new CreditCard(holder, "number1");
        creditCardManager.addCreditCard(c1);

        CreditCard c2 = new CreditCard(holder, "number2");
        creditCardManager.addCreditCard(c2);

        Collection<CreditCard> cards = creditCardManager.getCreditCardByName(holder);
        for (CreditCard cc : cards) {
            System.out.println(String.format("CreditCard{id=%s, name=%s, number=%s}", cc.getId(), cc.getName(), cc.getNumber()));
        }

        assert cards.size() == 2 : String.format("Expected %d but got %d", 2, cards.size());
    }
}
