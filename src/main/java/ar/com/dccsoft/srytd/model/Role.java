package ar.com.dccsoft.srytd.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	READ_ONLY {
		@Override
		public String toString() { return "Solo lectura"; }
	}, USER {
		@Override
		public String toString() { return "Usuario"; }
	}, ADMIN{
		@Override
		public String toString() { return "Administrador"; }
	};
	
    @JsonValue
    public String toValue() {
        return toString();
    }
}
