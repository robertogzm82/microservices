package com.nttdata.transaction.model;

public enum TransactionType {

	retiro // asociado transaction de cuentas bancarias( ahorro, cuentacorriente,plazofijo)
	,deposito // asociado a transacciones de cuentas bancarias( ahorro, cuentacorriente,plazofijo)
	,consumo // asociado de credito personal, empresarial y tarjetas de credito personal y empresarial
	,pago //  asociado a transacciones de productos credito personal, empresarial y tarjetas de credito personal y empresarial
	,comision // asociado a: credito personal, empresarial y tarjetas de credito personal y empresarial
	
}
