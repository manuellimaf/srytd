package ar.com.dccsoft.srytd.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProcessStatus {

	STARTED {
		@Override
		public String toString() { return "Iniciado"; }
	}, PROCESSED{
		@Override
		public String toString() { return "Mapeado"; }
	}, SENT{
		@Override
		public String toString() { return "Enviado"; }
	}, FINISHED{
		@Override
		public String toString() { return "Finalizado"; }
	}, FINISHED_OK{
		@Override
		public String toString() { return "OK"; }
	}, FINISHED_WARN{
		@Override
		public String toString() { return "Alertado"; }
	}, ERROR{
		@Override
		public String toString() { return "Error"; }
	};
	
    @JsonValue
    public String toValue() {
        return toString();
    }
}
