package jpay.domain;

import jpay.exception.DomainAddException;
import jpay.exception.DomainFindException;
import jpay.model.CreditCard;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class MockCreditCardSource implements CreditCardSource
{
    private long id = 1;
    private final ConcurrentMap<String, CreditCard> cards = new ConcurrentHashMap<String, CreditCard>();

    public MockCreditCardSource ()
    {
        addCreditCard(new CreditCard("name1", "number1"));
        addCreditCard(new CreditCard("name2", "number2"));
    }

    public void addCreditCard (final CreditCard cc)
    {
        String sid = String.valueOf(id++);

        if (cards.putIfAbsent(sid, cc) != null) {
            throw new DomainAddException(cc.getNumber());
        }

    }

    public CreditCard getCreditCardByNumber (final String number)
    {
        for (CreditCard cc : cards.values()) {
            if (number != null && number.equals(cc.getNumber())) {
                return cc;
            }
        }
        throw new DomainFindException(number);
    }

    public Collection<CreditCard> getCreditCardByName (String holder)
    {
        Collection<CreditCard> found = new HashSet<CreditCard>();
        for (CreditCard cc : cards.values()) {
            if (holder != null && holder.equals(cc.getName())) {
                found.add(cc);
            }
        }

        if (found.isEmpty()) {
            throw new DomainFindException(holder);
        }

        return found;
    }


    public Collection<CreditCard> getAllCreditCards ()
    {
        return cards.values();
    }
}
