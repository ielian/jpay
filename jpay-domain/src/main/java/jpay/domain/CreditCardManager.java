package jpay.domain;

import jpay.model.CreditCard;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
public class CreditCardManager
{
    @Inject
    CreditCardSource creditCardSource;

    public CreditCardSource getCreditCardSource ()
    {
        return creditCardSource;
    }

    public void setCreditCardSource (CreditCardSource source)
    {
        this.creditCardSource = source;
    }

    public void addCreditCard (CreditCard cc)
    {
        creditCardSource.addCreditCard(cc);
    }

    public CreditCard getCreditCardByNumber (String number)
    {
        return creditCardSource.getCreditCardByNumber(number);
    }

    public Collection<CreditCard> getCreditCardByName (String holder)
    {
        return creditCardSource.getCreditCardByName(holder);
    }

    public Collection<CreditCard> getAllCreditCards ()
    {
        return creditCardSource.getAllCreditCards();
    }
}
