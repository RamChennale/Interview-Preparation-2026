class Bank {
    roi(){
        return 0;
    }
}

class AXIS extends Bank{
    roi() {
        return 9.0;
    }
}


class SBI extends Bank{    
    roi() {
        return 8.0;
    }
}

class HDFC extends Bank{
    constructor(){
        super();
    }
   
}

sbi = new SBI();
console.log(sbi.roi());

ax = new AXIS();
console.log(ax.roi());

hdfc = new HDFC();
console.log(hdfc.roi());