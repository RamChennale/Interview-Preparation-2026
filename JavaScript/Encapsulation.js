class student {

    constructor(){
        let name , marks; 
    }

    getName(){
        return this.name;
    }
    setName(name){
        this.name = name;
    }

    getmarks(){
        return this.marks;
    }
    setMarks(marks){
        this.marks = marks;
    }
}
let std = new student();
std.setName("Ram");
std.setMarks(11);
console.log(std.getName(), std.getmarks());
