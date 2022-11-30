package com.sigecap.sigecapexamenbackend.util;

public interface Constantes {

    public final String ROL_OK = "20210008";
    public final String ESTADO_ACTIVO = "ACT";
    public final String ESTADO_INACTIVO = "INA";

    public static final String TABLE_PREGUNTA= "sge_tm_pregunta";
    public static final String ID_TABLE_PREGUNTA= "id_pregunta";

    public static final String TABLE_EXAMEN= "sge_tz_examen";
    public static final String ID_TABLE_EXAMEN= "id_examen";

    public static final String TABLE_EXAMEN_PREGUNTA= "sge_tz_examen_por_pregunta";
    public static final String ID_TABLE_EXAMEN_PREGUNTA= "id_examen_pregunta";

    public static final String TABLE_EXAMEN_APERTURA= "sge_tz_examen_apertura";
    public static final String ID_TABLE_EXAMEN_APERTURA= "id_examen_apertura";

    public static final String TABLE_RESPUESTA= "sge_tm_respuesta";
    public static final String ID_TABLE_RESPUESTA= "id_respuesta";

    public static final String CODIGO_EMPRESA = "01";

    // TIPOS DE PREGUNTAS

    public static final String INDICADOR_RESPUESTA_CORRECTA = "S";
    public static final String INDICADOR_RESPUESTA_INCORRECTA = "N";

    public static final String RESPUESTA_VERDADERO = "V";
    public static final String RESPUESTA_FALSO = "F";

    public static final String OPCION_UNICA = "20220701";
    public static final String OPCION_MULTIPLE = "20220702";
    public static final String OPCION_VERDADERO_FALSO = "20220703";
    public static final String OPCION_NUMERICA = "20220704";
    public static final String OPCION_RESPUESTA = "20220705";

    // ESTADOS APERTURA EXAMEN

    public static final String CREADO_APERTURA_EXAMEN = "CRE";
    public static final String INICIADO_APERTURA_EXAMEN = "INI";
    public static final String CERRADO_APERTURA_EXAMEN = "CER";


}
