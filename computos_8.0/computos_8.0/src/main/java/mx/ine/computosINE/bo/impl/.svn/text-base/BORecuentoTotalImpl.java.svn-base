 /**
 * @(#)BORecuentoTotalImpl.java 12/05/2017
 * <p>
 * Copyright (C) 2016 Instituto Nacional Electoral (INE).
 * <p>
 * Todos los derechos reservados.
 */
package mx.ine.computosINE.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.common.enums.EnumAmbitoDetalleProceso;
import mx.ine.common.ws.candidatos.dto.response.DTOCandidatoWS;
import mx.ine.computosINE.bo.BORecuentoTotalInterface;
import mx.ine.computosINE.dto.form.FormRecuentoTotal;
import mx.ine.computosINE.dto.form.FormReporteRecuento;
import mx.ine.computosINE.dto.helper.HLPEntornoGeografico;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;

 /**
 * Clase que provee la implementación de los métodos de negocio 
 * para el módulo de recuento total
 * 
 * @author José Antonio López Torres
 * @since 12/05/2017
 * @copyright Direccion de sistemas - INE
 */
@Component("boRecuentoTotal")
@Scope("prototype")
public class BORecuentoTotalImpl implements BORecuentoTotalInterface{

    /**
    * {@inheritDoc}
    */
    @Override
    public List<DTOCandidatoWS> obtenTipoCandidaturas() {
        //Lista
        List<DTOCandidatoWS> lista = new ArrayList<>();
        //Candidaturas
        DTOCandidatoWS diputado = new DTOCandidatoWS();
        diputado.setIdTipoCandidatura(Constantes.ID_TIPO_CAND_DIPUTADO_MR);
        diputado.setNombreCortoTipoCandidatura(Constantes.NOM_CORTO_DIPUTADO_MR);
        diputado.setNombreLargoTipoCandidatura(Constantes.NOM_LARGO_DIPUTADO_MR);
        DTOCandidatoWS gobernador = new DTOCandidatoWS();
        gobernador.setIdTipoCandidatura(Constantes.ID_TIPO_CAND_GOBERNADOR);
        gobernador.setNombreCortoTipoCandidatura(Constantes.NOM_GOBERNADOR);
        gobernador.setNombreLargoTipoCandidatura(Constantes.NOM_LARGO_GOBERNADOR);
        DTOCandidatoWS ayuntamiento = new DTOCandidatoWS();
        ayuntamiento.setIdTipoCandidatura(Constantes.ID_TIPO_CAND_AYUNTAMIENTO);
        ayuntamiento.setNombreCortoTipoCandidatura(Constantes.NOM_CORTO_AYUNTAMIENTO);
        ayuntamiento.setNombreLargoTipoCandidatura(Constantes.NOM_LARGO_AYUNTAMIENTO);
        DTOCandidatoWS regidor = new DTOCandidatoWS();
        regidor.setIdTipoCandidatura(Constantes.ID_TIPO_CAND_REGIDURIA_MR);
        regidor.setNombreCortoTipoCandidatura(Constantes.NOM_REGIDOR_MR);
        regidor.setNombreLargoTipoCandidatura(Constantes.NOM_LARGO_REGIDURIA_MR);
        //Agregar
        lista.add(diputado);
        lista.add(gobernador);
        lista.add(ayuntamiento);
        lista.add(regidor);
        return lista;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<String> obtenRemplazaQuery(Integer entorno, FormRecuentoTotal dto) {
        //Lista
        List<String> lista = new ArrayList<>();
        StringBuilder remSelect = new StringBuilder(" ");
        StringBuilder remGeneral = new StringBuilder(" ");
        StringBuilder remWhere = new StringBuilder(" ");
        StringBuilder remOrderGroup = new StringBuilder(" ");
        StringBuilder remJoinAB = new StringBuilder(" ");
        StringBuilder remJoinAC = new StringBuilder(" ");
        //Partes de query a remplazar
        if(entorno.intValue() == Constantes.ENTORNO_ESTADO){
            remSelect.append("A.ID_ESTADO ID ");
        }else if(entorno.intValue() == Constantes.ENTORNO_DISTRITO){
            remSelect.append("A.ID_DISTRITO ID ");
            remGeneral.append(" A.ID_DISTRITO, ");
            remOrderGroup.append(", A.ID_DISTRITO");
            remJoinAB.append(" AND A.ID_DISTRITO=B.ID_DISTRITO ");
            remJoinAC.append(" AND A.ID_DISTRITO=C.ID_DISTRITO ");
        }else if(entorno.intValue() == Constantes.ENTORNO_MUNICIPIO){
            //Candidatura ayuntamiento
            if(dto.getIdCandidatura().intValue() != Constantes.ID_TIPO_CAND_REGIDURIA_MR){
                remSelect.append("A.ID_MUNICIPIO ID ");
                remGeneral.append(" A.ID_MUNICIPIO, ");
                remOrderGroup.append(", A.ID_MUNICIPIO");
                remJoinAB.append(" AND A.ID_MUNICIPIO=B.ID_MUNICIPIO ");
                remJoinAC.append(" AND A.ID_MUNICIPIO=C.ID_MUNICIPIO ");
            //Candidatura Regidor (Lista de municipios antes de cargar tabla)
            }else{
                remSelect.append("A.ID_MUNICIPIO ID ");
                remGeneral.append(" A.ID_MUNICIPIO, A.ID_REGIDURIA, ");
                remOrderGroup.append(", A.ID_MUNICIPIO, A.ID_REGIDURIA ");
                remJoinAB.append(" AND A.ID_MUNICIPIO=B.ID_MUNICIPIO AND A.ID_REGIDURIA=B.ID_REGIDURIA ");
                remJoinAC.append(" AND A.ID_MUNICIPIO=C.ID_MUNICIPIO AND A.ID_REGIDURIA=C.ID_REGIDURIA ");
            }
        }else if(entorno.intValue() == Constantes.ENTORNO_DEMARCACION){
            remSelect.append("A.ID_REGIDURIA ID ");
            remGeneral.append(" A.ID_MUNICIPIO, A.ID_REGIDURIA, ");
            remWhere.append(" AND A.ID_MUNICIPIO=").append(dto.getIdMunicipio().toString());
            remOrderGroup.append(", A.ID_MUNICIPIO, A.ID_REGIDURIA ");
            remJoinAB.append(" AND A.ID_MUNICIPIO=B.ID_MUNICIPIO AND A.ID_REGIDURIA=B.ID_REGIDURIA ");
            remJoinAC.append(" AND A.ID_MUNICIPIO=C.ID_MUNICIPIO AND A.ID_REGIDURIA=C.ID_REGIDURIA ");
        }
        lista.add(remSelect.toString());
        lista.add(remGeneral.toString());
        lista.add(remWhere.toString());
        lista.add(remOrderGroup.toString());
        lista.add(remJoinAB.toString());
        lista.add(remJoinAC.toString());
        return lista;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String obtenRemplazaQueryDeletePK(Integer entorno, FormRecuentoTotal dto) {
        StringBuilder sb = new StringBuilder(" ");
        sb.append(" pk.idProcesoElectoral=:proceso ");
        sb.append(" and pk.idDetalleProceso=:detalle ");
        sb.append(" and pk.idEstado=:estado ");
        sb.append(" and pk.idTipoCandidatura IN (:candidatura1, :candidatura2) ");
        //Solo cuando el entorno es distinto a estado
        if(entorno.intValue() != Constantes.ENTORNO_ESTADO){
            if(entorno.intValue() == Constantes.ENTORNO_DISTRITO){
                sb.append(" and pk.idDistrito in ( ");
            }else if(entorno.intValue() == Constantes.ENTORNO_MUNICIPIO){
                sb.append(" and pk.idMunicipio in ( ");
            }else if(entorno.intValue() == Constantes.ENTORNO_DEMARCACION){
                sb.append(" and pk.idMunicipio = ");
                sb.append(dto.getIdMunicipio().toString());
                sb.append(" and pk.idRegiduria in ( ");
            }
            for(HLPEntornoGeografico geo : dto.getListaGeografiaSelect()){
                sb.append(geo.getId().toString()).append(", ");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(" )");
        }
        return sb.toString();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String obtenRemplazaQueryDeleteID(Integer entorno,
            FormRecuentoTotal dto) {
        StringBuilder sb = new StringBuilder(" ");
        sb.append(" id.idProcesoElectoral=:proceso ");
        sb.append(" and id.idDetalleProceso=:detalle ");
        sb.append(" and id.idEstado=:estado ");
        sb.append(" and id.idTipoCandidatura IN (:candidatura1, :candidatura2) ");
        //Solo cuando el entorno es distinto a estado
        if(entorno.intValue() != Constantes.ENTORNO_ESTADO){
            if(entorno.intValue() == Constantes.ENTORNO_DISTRITO){
                sb.append(" and id.idDistrito in ( ");
            }else if(entorno.intValue() == Constantes.ENTORNO_MUNICIPIO){
                sb.append(" and id.idMunicipio in ( ");
            }else if(entorno.intValue() == Constantes.ENTORNO_DEMARCACION){
                sb.append(" and id.idMunicipio = ");
                sb.append(dto.getIdMunicipio().toString());
                sb.append(" and id.idRegiduria in ( ");
            }
            for(HLPEntornoGeografico geo : dto.getListaGeografiaSelect()){
                sb.append(geo.getId().toString()).append(", ");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(" )");
        }
        return sb.toString();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String obtenRemplazaQueryUpdate(Integer entorno,
            FormRecuentoTotal dto) {
        StringBuilder sb = new StringBuilder(" ");
        sb.append(" id.idProcesoElectoral=:proceso ");
        sb.append(" and id.idDetalleProceso=:detalle ");
        sb.append(" and id.idEstado=:estado ");
        sb.append(" and id.idTipoCandidatura IN (:candidatura1, :candidatura2) ");
        sb.append(" and capturada=1 and idEstatus=1 ");
        //Solo cuando el entorno es distinto a estado
        if(entorno.intValue() != Constantes.ENTORNO_ESTADO){
            if(entorno.intValue() == Constantes.ENTORNO_DISTRITO){
                sb.append(" and id.idDistrito in ( ");
            }else if(entorno.intValue() == Constantes.ENTORNO_MUNICIPIO){
                sb.append(" and id.idMunicipio in ( ");
            }else if(entorno.intValue() == Constantes.ENTORNO_DEMARCACION){
                sb.append(" and id.idMunicipio = ");
                sb.append(dto.getIdMunicipio().toString());
                sb.append(" and id.idRegiduria in ( ");
            }
            for(HLPEntornoGeografico geo : dto.getListaGeografiaSelect()){
                sb.append(geo.getId().toString()).append(", ");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(" )");
        }
        return sb.toString();
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void filtarCandidaturasReporte(
            List<DTOCandidatoWS> listaCandidaturas, String version) {
        List<DTOCandidatoWS> listaEliminar = new ArrayList<>();
        //Si es JL solo deben estar las candidaturas de gobernador y diputado MR
        //Si es JM solo deben estar las candidaturas de ayuntamiento y regidor MR
        for(DTOCandidatoWS can : listaCandidaturas){
            if(version.equals(Constantes.JL) && can.getIdTipoCandidatura().intValue()
                    == Constantes.ID_TIPO_CAND_AYUNTAMIENTO){
                listaEliminar.add(can);
                continue;
            }
            if(version.equals(Constantes.JL) && can.getIdTipoCandidatura().intValue()
                    == Constantes.ID_TIPO_CAND_REGIDURIA_MR){
                listaEliminar.add(can);
                continue;
            }
            if(version.equals(Constantes.JM) && can.getIdTipoCandidatura().intValue()
                    == Constantes.ID_TIPO_CAND_GOBERNADOR){
                listaEliminar.add(can);
                continue;
            }
            if(version.equals(Constantes.JM) && can.getIdTipoCandidatura().intValue()
                    == Constantes.ID_TIPO_CAND_DIPUTADO_MR){
                listaEliminar.add(can);
                continue;
            }
        }
        //Filtrar
        if(CollectionUtils.isNotEmpty(listaEliminar)){
            listaCandidaturas.removeAll(listaEliminar);
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<String> obtenRemplazaQueryReporte(Integer entorno, FormReporteRecuento dto) {
        //Lista
        List<String> lista = new ArrayList<>();
        StringBuilder remSelect = new StringBuilder(" ");
        StringBuilder remGeneral = new StringBuilder(" ");
        StringBuilder remOrderGroup = new StringBuilder(" ");
        StringBuilder remWhere = new StringBuilder(" ");
        StringBuilder remJoinAB = new StringBuilder(" ");
        StringBuilder remJoinAC = new StringBuilder(" ");
        StringBuilder remJoinGeoB = new StringBuilder(" ");
        StringBuilder remJoinGeoC = new StringBuilder(" ");
        StringBuilder remWhereGeo = new StringBuilder(" ");
        StringBuilder remOrderGeo = new StringBuilder(" ");
        StringBuilder remTabla = new StringBuilder(" ");
        //Partes de query a remplazar
        if(entorno.intValue() == Constantes.ENTORNO_ESTADO){
            //SELECT
            remSelect.append("A.ID_ESTADO ID, A.NOMBRE_ESTADO NOMBRE, ");
            remSelect.append(" CASE WHEN B.ID_ESTADO IS NULL THEN 'No' ELSE 'Sí' END CAUSALA, ");
            remSelect.append(" CASE WHEN C.ID_ESTADO IS NULL THEN 'No' ELSE 'Sí' END CAUSALB ");
            //TABLA
            remTabla.append(" GEOGRAFICO.ESTADOS ");
            //Columna
            dto.setEncabezadoColumn(Utilidades.mensajeProperties("etiqueta_sistema_entidad"));
        }else if(entorno.intValue() == Constantes.ENTORNO_DISTRITO){
            if(dto.getAmbito() == EnumAmbitoDetalleProceso.F){
                //SELECT
                remSelect.append("A.ID_DISTRITO_FEDERAL ID, A.ID_DISTRITO_FEDERAL||' - '||A.CABECERA_DISTRITAL_FEDERAL NOMBRE, ");
                remSelect.append(" CASE WHEN B.ID_DISTRITO IS NULL THEN 'No' ELSE 'Sí' END CAUSALA, ");
                remSelect.append(" CASE WHEN C.ID_DISTRITO IS NULL THEN 'No' ELSE 'Sí' END CAUSALB ");
                //TABLA
                remTabla.append(" GEOGRAFICO.DISTRITOS_FEDERALES ");
                //JOIN GEO B
                remJoinGeoB.append(" AND A.ID_DISTRITO_FEDERAL=B.ID_DISTRITO ");
                //JOIN GEO C
                remJoinGeoC.append(" AND A.ID_DISTRITO_FEDERAL=C.ID_DISTRITO ");
                //ORDER GEO
                remOrderGeo.append(", A.ID_DISTRITO_FEDERAL");
            }else{
                //SELECT
                remSelect.append("A.ID_DISTRITO_LOCAL ID, A.ID_DISTRITO_LOCAL||' - '||A.CABECERA_DISTRITAL_LOCAL NOMBRE, ");
                remSelect.append(" CASE WHEN B.ID_DISTRITO IS NULL THEN 'No' ELSE 'Sí' END CAUSALA, ");
                remSelect.append(" CASE WHEN C.ID_DISTRITO IS NULL THEN 'No' ELSE 'Sí' END CAUSALB ");
                //TABLA
                remTabla.append(" GEOGRAFICO.DISTRITOS_LOCALES ");
                //JOIN GEO B
                remJoinGeoB.append(" AND A.ID_DISTRITO_LOCAL=B.ID_DISTRITO ");
                //JOIN GEO C
                remJoinGeoC.append(" AND A.ID_DISTRITO_LOCAL=C.ID_DISTRITO ");
                //ORDER GEO
                remOrderGeo.append(", A.ID_DISTRITO_LOCAL");
            }
            //GENERAL
            remGeneral.append(" A.ID_DISTRITO, ");
            //ORDER GROUP
            remOrderGroup.append(", A.ID_DISTRITO");
            //JOIN AB
            remJoinAB.append(" AND A.ID_DISTRITO=B.ID_DISTRITO ");
            //JOIN AC
            remJoinAC.append(" AND A.ID_DISTRITO=C.ID_DISTRITO ");
            //Columna
            dto.setEncabezadoColumn(Utilidades.mensajeProperties("etiqueta_sistema_distrito"));
        }else if(entorno.intValue() == Constantes.ENTORNO_MUNICIPIO){
            if(dto.getAmbito() == EnumAmbitoDetalleProceso.F){
                //SELECT
                remSelect.append("A.ID_MUNICIPIO_FEDERAL ID, A.ID_MUNICIPIO_FEDERAL||' - '||A.NOMBRE_MUNICIPIO_FEDERAL NOMBRE, ");
                remSelect.append(" CASE WHEN B.ID_MUNICIPIO IS NULL THEN 'No' ELSE 'Sí' END CAUSALA, ");
                remSelect.append(" CASE WHEN C.ID_MUNICIPIO IS NULL THEN 'No' ELSE 'Sí' END CAUSALB ");
                //TABLA
                remTabla.append(" GEOGRAFICO.MUNICIPIOS_FEDERALES ");
                //JOIN GEO B
                remJoinGeoB.append(" AND A.ID_MUNICIPIO_FEDERAL=B.ID_MUNICIPIO ");
                //JOIN GEO C
                remJoinGeoC.append(" AND A.ID_MUNICIPIO_FEDERAL=C.ID_MUNICIPIO ");
                //ORDER GEO
                remOrderGeo.append(", A.ID_MUNICIPIO_FEDERAL");
                //WHERE GEO
                remWhereGeo.append(" AND A.ID_MUNICIPIO_FEDERAL=").append(
                        dto.getUsuario().getIdMunicipioSeleccionado().toString());
            }else{
                //SELECT
                remSelect.append("A.ID_MUNICIPIO_LOCAL ID, A.ID_MUNICIPIO_LOCAL||' - '||A.NOMBRE_MUNICIPIO_LOCAL NOMBRE, ");
                remSelect.append(" CASE WHEN B.ID_MUNICIPIO IS NULL THEN 'No' ELSE 'Sí' END CAUSALA, ");
                remSelect.append(" CASE WHEN C.ID_MUNICIPIO IS NULL THEN 'No' ELSE 'Sí' END CAUSALB ");
                //TABLA
                remTabla.append(" GEOGRAFICO.MUNICIPIOS_LOCALES ");
                //JOIN GEO B
                remJoinGeoB.append(" AND A.ID_MUNICIPIO_LOCAL=B.ID_MUNICIPIO ");
                //JOIN GEO C
                remJoinGeoC.append(" AND A.ID_MUNICIPIO_LOCAL=C.ID_MUNICIPIO ");
                //ORDER GEO
                remOrderGeo.append(", A.ID_MUNICIPIO_LOCAL");
                //WHERE GEO
                remWhereGeo.append(" AND A.ID_MUNICIPIO_LOCAL=").append(
                        dto.getUsuario().getIdMunicipioSeleccionado().toString());
            }
            //GENERAL
            remGeneral.append(" A.ID_MUNICIPIO, ");
            //ORDER GROUP
            remOrderGroup.append(", A.ID_MUNICIPIO");
            //JOIN AB
            remJoinAB.append(" AND A.ID_MUNICIPIO=B.ID_MUNICIPIO ");
            //JOIN
            remJoinAC.append(" AND A.ID_MUNICIPIO=C.ID_MUNICIPIO ");
            //WHERE
            remWhere.append(" AND A.ID_MUNICIPIO=").append(
                    dto.getUsuario().getIdMunicipioSeleccionado().toString());
            //Columna
            dto.setEncabezadoColumn(Utilidades.mensajeProperties("etiqueta_sistema_municipio"));
        }else if(entorno.intValue() == Constantes.ENTORNO_DEMARCACION){
            //SELECT
            remSelect.append("A.ID_REGIDURIA ID, A.NOMBRE_REGIDURIA NOMBRE, ");
            remSelect.append(" CASE WHEN B.ID_REGIDURIA IS NULL THEN 'No' ELSE 'Sí' END CAUSALA, ");
            remSelect.append(" CASE WHEN C.ID_REGIDURIA IS NULL THEN 'No' ELSE 'Sí' END CAUSALB ");
            //TABLA
            remTabla.append(" GEOGRAFICO.REGIDURIAS ");
            //JOIN GEO B
            remJoinGeoB.append(" AND A.ID_MUNICIPIO_LOCAL=B.ID_MUNICIPIO AND A.ID_REGIDURIA=B.ID_REGIDURIA ");
            //JOIN GEO C
            remJoinGeoC.append(" AND A.ID_MUNICIPIO_LOCAL=C.ID_MUNICIPIO AND A.ID_REGIDURIA=C.ID_REGIDURIA ");
            //ORDER GEO
            remOrderGeo.append(", A.ID_MUNICIPIO_LOCAL, A.ID_REGIDURIA ");
            //WHERE GEO
            remWhereGeo.append(" AND A.ID_MUNICIPIO_LOCAL=").append(
                    dto.getUsuario().getIdMunicipioSeleccionado().toString());
            //GENERAL
            remGeneral.append(" A.ID_MUNICIPIO, A.ID_REGIDURIA, ");
            //ORDER GROUP
            remOrderGroup.append(", A.ID_MUNICIPIO, A.ID_REGIDURIA ");
            //JOIN AB
            remJoinAB.append(" AND A.ID_MUNICIPIO=B.ID_MUNICIPIO AND A.ID_REGIDURIA=B.ID_REGIDURIA ");
            //JOIN
            remJoinAC.append(" AND A.ID_MUNICIPIO=C.ID_MUNICIPIO AND A.ID_REGIDURIA=C.ID_REGIDURIA ");
            //WHERE
            remWhere.append(" AND A.ID_MUNICIPIO=").append(
                    dto.getUsuario().getIdMunicipioSeleccionado().toString());
            //Columna
            dto.setEncabezadoColumn(Utilidades.mensajeProperties("etiqueta_sistema_demarcacion"));
        }
        lista.add(remSelect.toString());
        lista.add(remGeneral.toString());
        lista.add(remOrderGroup.toString());
        lista.add(remWhere.toString());
        lista.add(remJoinAB.toString());
        lista.add(remJoinAC.toString());
        lista.add(remJoinGeoB.toString());
        lista.add(remJoinGeoC.toString());
        lista.add(remWhereGeo.toString());
        lista.add(remOrderGeo.toString());
        lista.add(remTabla.toString());
        return lista;
    }

}
