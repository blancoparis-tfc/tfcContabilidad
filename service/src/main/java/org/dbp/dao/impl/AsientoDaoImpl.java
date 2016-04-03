package org.dbp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.bom.contabilidad.CuentaContable;
import org.dbp.bom.contabilidad.LineaAsiento;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.AsientoDao;
import org.dbp.dto.contabilidad.AsientoFiltro;
import org.dbp.sql.contabilidad.ResumenAsiento;
import org.dbp.utils.JqplFilterUtils;
import org.springframework.stereotype.Repository;

@Repository
public class AsientoDaoImpl extends GenericDaoImpl<Asiento,Long> implements AsientoDao{

	@PersistenceContext private EntityManager entityManager;
	
	public AsientoDaoImpl() {
		super(Asiento.class);
	}
	
	@Override
	public List<ResumenAsiento> consultarFiltro(AsientoFiltro filtro){
		// Ver lo mismo con el los criterios.
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumenAsiento> query = builder.createQuery(ResumenAsiento.class);
		Root<Asiento> fAsiento = query.from(Asiento.class);
		Join<Asiento,LineaAsiento> jLinea= fAsiento.join("lineas",JoinType.INNER);
		Join<LineaAsiento,CuentaContable> jCuentaContable = jLinea.join("cuenta",JoinType.INNER);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
				.addParametro(Long.class,"id",fAsiento,filtro.getId())
				.addParametro(String.class, "descripcion", fAsiento, filtro.getDescripcion())
				.addParametro(String.class,"cuenta",jCuentaContable,filtro.getCuenta())
				.addParametro(String.class,"concepto",jLinea,filtro.getConcepto());
		query.groupBy(fAsiento.get("id"),fAsiento.get("descripcion"))
			 .where(jqplFilterUtils.crearLosPredicados())
			 .select(builder.construct(ResumenAsiento.class, 
				fAsiento.get("id"),fAsiento.get("descripcion"),
				builder.sum(jLinea.get("importe"))));
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

	

	
	
}
