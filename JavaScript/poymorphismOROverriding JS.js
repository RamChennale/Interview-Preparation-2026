class shape {
    draw(){
            return " draw from shape class "   
        }
}

class Squar extends shape{
    draw(){
            return " draw from Squar class "   
        }
}

class Circle extends shape{
    draw(){
            return " draw from Circle class "   
        }
}

let s = new shape();
console.log(s.draw());

let sq = new Squar();
console.log(sq.draw());

let cl = new Circle();
console.log(cl.draw());

