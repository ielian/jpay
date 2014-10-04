package jpay.domain;

import jpay.exception.DomainFindException;
import jpay.model.CreditCard;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * CreditCardJpaSource
 */
public class CreditCardJpaSource implements CreditCardSource
{

    @PersistenceContext(unitName = "jpayPersistenceUnit")
    private EntityManager entityManager;

    public void setEntityManager (EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addCreditCard (CreditCard cc)
    {
        entityManager.persist(cc);
        entityManager.flush();
    }

    @Transactional(readOnly = true)
    public CreditCard getCreditCardByNumber (String number)
    {
        if (number != null) {
            Query q = entityManager.createQuery("select cc from CreditCard cc where cc.number = :number");
            q.setParameter("number", number);
            return (CreditCard)q.getSingleResult();

        }

        throw new DomainFindException(number);
    }


    @Transactional(readOnly = true)
    public Collection<CreditCard> getCreditCardByName (String name)
    {
        if (name != null) {
            Query q = entityManager.createQuery("select cc from CreditCard cc where cc.name = :name");
            q.setParameter("name", name);
            return q.getResultList();
        }

        throw new DomainFindException(name);
    }

    @Transactional(readOnly = true)
    public Collection<CreditCard> getAllCreditCards ()
    {
        Query q = entityManager.createQuery("select cc from CreditCard cc");
        return q.getResultList();
    }


}
