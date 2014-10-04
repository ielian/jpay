package jpay.model;

import org.apache.openjpa.persistence.jdbc.Unique;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="cards")
@XmlRootElement
public class CreditCard implements Comparable<CreditCard>
{
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    private String name;

    private String number;

    public CreditCard ()
    {

    }

    public CreditCard (String name, String number)
    {
        this.name = name;
        this.number = number;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    @Override
    public int compareTo (CreditCard other)
    {
        if (name != null && number != null && name.equals(other.getName()) && number.equals(other.getNumber()))
        {
            return 0;
        }
        return -1;
    }

    public String toString ()
    {
        return String.format("CreditCard{id=%s, name=%s, number=%s}", getId(), getName(), getNumber());
    }
}
