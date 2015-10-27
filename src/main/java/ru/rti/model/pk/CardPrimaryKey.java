package ru.rti.model.pk;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class CardPrimaryKey implements Serializable {

	private static final long serialVersionUID = 6908917591694183901L;

	public CardPrimaryKey() {
		
	}

	public CardPrimaryKey(String pan) {
		this(pan, (byte) 0);
	}

	public CardPrimaryKey(String pan, byte mbr) {
		setPan(pan);
		setMbr(mbr);
	}

	@Column(length = 20)
	private String pan;

	private byte mbr;

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public byte getMbr() {
		return mbr;
	}

	public void setMbr(byte mbr) {
		this.mbr = mbr;
	}

	@Override
	public int hashCode() {
		return pan.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof CardPrimaryKey) {
			CardPrimaryKey pkObject = (CardPrimaryKey) obj;
			if (pan.equals(pkObject.pan))
				return mbr == pkObject.mbr;
		}
		return false;
	}

	@Override
	public String toString() {
		return pan + "-" + mbr;
	}

}