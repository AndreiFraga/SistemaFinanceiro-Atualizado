/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


/**
 *
 * @author Discentes
 */
public class ModeloConsulta {
   float totalDespesas;
   float totalReceitas;
   float saldo;

    public ModeloConsulta(float totalDespesas, float totalReceitas, float saldo) {
        this.totalDespesas = totalDespesas;
        this.totalReceitas = totalReceitas;
        this.saldo = saldo;
    }

    public float getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(float totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public float getTotalReceitas() {
        return totalReceitas;
    }

    public void setTotalReceitas(float totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
   
   
   
}
