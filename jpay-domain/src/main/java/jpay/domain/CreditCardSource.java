package jpay.domain;

import jpay.model.CreditCard;

import java.util.Collection;

/**
 * CreditCardSource
 */
public interface CreditCardSource
{
    public void addCreditCard (CreditCard cc);

    public CreditCard getCreditCardByNumber (String number);

    public Collection<CreditCard> getCreditCardByName (String holder);

    public Collection<CreditCard> getAllCreditCards ();
}
