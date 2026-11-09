class student
{    // setDetails(id, name, grade){
    //     this.id = id,
    //     this.name = name,
    //     this.grade = grade;
    // }

    constructor(id, name, grade){
        this.id = id,
        this.name = name,
        this.grade = grade;
    }

    display(){
        console.log(this.id, this.name, this.grade);
    }
}

let  std = new student(1,"ram","A");
//let  std = new student();
//std.setDetails(1,"ram","A");
std.display();


//inhertance 
class A {
    a =10;
    display1(){
        console.log(this.a)
    }
}

class B extends A {
    b =20;
    show(){
        console.log(this.b);
    }
}

bObj = new  B();
bObj.display1();
bObj.show();

//mehod overriding 