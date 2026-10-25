
package modelo;

public class EmpleadoFijo extends Empleado {

    private double salarioMensual;
    private double bonificacionMensual;

    public EmpleadoFijo(String dpi, String nombre,
                        double salarioMensual,
                        double bonificacionMensual) {

        super(dpi, nombre);
        this.salarioMensual = salarioMensual;
        this.bonificacionMensual = bonificacionMensual;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public double getBonificacionMensual() {
        return bonificacionMensual;
    }

    @Override
    public double calcularPago() {
        return salarioMensual + bonificacionMensual;
    }

    @Override
    public String obtenerTipo() {
        return "Fijo";
    }
}