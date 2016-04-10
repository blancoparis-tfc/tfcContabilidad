export class Paginar{
    public margenPaginas:number;
    constructor(
      public numeroDePaginas:number = 0,
      public paginas:Array<number> = [],
      public pagina:number = 1,
      public filasPorVentana:number = 5,
      public paginasVentana:number = 5
    ){
      this.margenPaginas = this.trunc(this.filasPorVentana/2);
    }

    inicializarPaginacion(filas:Array<any>){
      this.pagina=1;
      this.inicializarPaginas(filas);
    }

    inicializarPaginas(filas:Array<any>){
      this.paginas=[];
      if(filas.length>0){
        this.numeroDePaginas = Math.ceil(filas.length / this.filasPorVentana);
        for(var i:number=0;i< this.paginasVentana && i<this.numeroDePaginas;i++){
          this.paginas.push(i+1);
        }
      }else{
        this.numeroDePaginas = 1;
      }
    }

    paginar(pagina:number,filas:Array<any>){
      this.pagina = pagina;
      let limite = this.pagina * this.paginasVentana;
      if(this.isCambiarVentana(pagina)){
          this.establecerPaginas(pagina);
      }else if(pagina<=this.margenPaginas){
          this.inicializarPaginas(filas);
      }
      return filas.slice(limite - this.paginasVentana,limite);
    }

    private isCambiarVentana(pagina:number):boolean{
      return pagina>this.margenPaginas  // Miramos que no estemos en el primer elemento de la ventana.
          && ( this.paginas[0]==pagina                        // Es el primer elemento del array.
            || this.paginas[this.paginas.length-1]==pagina   // Es el ultimo elemento del array.
            || pagina == this.numeroDePaginas                // Que es la ultima
          ) // TODO Mirar si podemos quitar la ultima condición
          && ( this.paginas[0]==pagina  // O retrocedamos
            || this.paginas[this.paginas.length-1]<this.numeroDePaginas  // Miramos que no estemos en la ultima sección de la pagina.
          )
          ;
    }

    private establecerPaginas(pagina:number){
      var avance:boolean = this.paginas[0]!=pagina,
          desplazamiento=this.pagina - this.margenPaginas - this.paginas[0],
          sobrantes = pagina + this.margenPaginas - this.numeroDePaginas;  // Calculamos los números.
      for(var i=0;i<sobrantes;i++){this.paginas.pop();}
      this.paginas=this.paginas.map(num=>num+desplazamiento);
      for(var i=this.paginas.length;i< this.paginasVentana && !avance;i++){ // TODO: He tenido que cambiar, el tema que es una avance
        this.paginas.push(this.paginas[i-1]+1);
      }
    }


    //UTIL
    private trunc (x:number):number{
      return x < 0 ? Math.ceil(x) : Math.floor(x);
    }
}
