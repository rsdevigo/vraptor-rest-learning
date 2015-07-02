package net.sigfap.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import net.sigfap.core.domain.Edital;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class EditalDAO extends HibernateDAO<Edital, Long> {

	/**
	 * @deprecated CDI eyes
	 */
	protected EditalDAO() {
		super();
	}

}
