<template>
  <div class="app-container">
    <div class="banner">
      <h2>计算器</h2>
    </div>
    <div class="content">
      <div>
        {{ resultField }}
      </div>
      <input class="clear" type="button" value="C" @click="clearValue()"/>
      <input class="clear" type="button" value="⬅" @click="back()"/>
      <input class="calculate" type="button" value="=" @click="calculate()"/>
      <br/>
      <br/>

      <input id="square" class="symbol" style="font-size:20px" type="button" value="x^2" @click="square()"/>
      <input id="cube" class="symbol" style="font-size:20px" type="button" value="x^3" @click="cube()"/>
      <input class="number" type="button" value="1" @click="getv(1,'num')"/>
      <input class="number" type="button" value="2" @click="getv(2,'num')"/>
      <input class="number" type="button" value="3" @click="getv(3,'num')"/>
      <input class="symbol" type="button" value="+" @click="getv('+','sym')"/>
      <br/>
      <input class="symbol" style="font-size:20px" type="button" value="√" @click="sqrt()"/>
      <input class="symbol" style="font-size:20px" type="button" value="³√" @click="cbrt()"/>
      <input class="number" type="button" value="4" @click="getv(4,'num')"/>
      <input class="number" type="button" value="5" @click="getv(5,'num')"/>
      <input class="number" type="button" value="6" @click="getv(6,'num')"/>
      <input class="symbol" type="button" value="-" @click="getv('-','sym')"/>
      <br/>
      <input class="symbol" style="font-size:20px" type="button" value="ans" @click="ans()"/>
      <input class="symbol" style="font-size:20px" type="button" value="sin" @click="sin()"/>
      <input class="symbol" style="font-size:20px" type="button" value="cos" @click="cos()"/>
      <input class="number" type="button" value="7" @click="getv(7,'num')"/>
      <input class="number" type="button" value="8" @click="getv(8,'num')"/>
      <input class="number" type="button" value="9" @click="getv(9,'num')"/>
      <input class="symbol" type="button" value="*" @click="getv('*','sym')"/>
      <br/>
      <input class="symbol" style="font-size:20px" type="button" value="tan" @click="tan()"/>
      <input class="symbol" style="font-size:20px" type="button" value="^" @click="cal('^')"/>
      <input class="number" type="button" value="0" @click="getv(0,'num')"/>
      <input class="symbol" type="button" value="." @click="getv('.','sym')"/>
      <input class="symbol" type="button" value="/" @click="getv('/','sym')"/>
      <input class="symbol" type="button" value="(" @click="getv('(','sym')"/>
      <input class="symbol" type="button" value=")" @click="getv(')','sym')"/>

    </div>
    <div>
      <label>存款金额：</label>
      <input type="number" v-model="principal" />
      <br />
      <label>存款时长（单位：年）：</label>
      <input type="number" v-model="duration" />
      <br />
      <button @click="calculateInterest">计算利息</button>
      <br />
      <p>存款利息：{{ interest }}</p>
    </div>
    <div>
      <label>贷款金额：</label>
      <input type="number" v-model="principal2" />
      <br />
      <label>贷款时长（单位：年）：</label>
      <input type="number" v-model="duration2" />
      <br />
      <button @click="calculateloan">计算利息</button>
      <br />
      <p>贷款利息：{{ loanres }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Calculate",
  data() {
    return {
      resultField: "",
      endff: false,
      principal: null,
      duration: null,
      interest: null,
      principal2: null,
      duration2: null,
      loanres:null,
    };
  },
  methods: {
    clearValue() {
      this.resultField = "";
      this.endff = false
    },
    calculateInterest() {
      axios.post('/api/dep_int',{dep_money:this.principal,dep_date:this.duration})
          .then(response => {
            // 请求成功处理
            console.log(response.data);
            this.interest=response.data
          })
          .catch(error => {
            // 请求失败处理
            console.error(error);
          });
    },
    calculateloan() {
      axios.post('/api/loan_int',{loan_money:this.principal2,loan_date:this.duration2})
          .then(response => {
            // 请求成功处理
            console.log(response.data);
            this.loanres=response.data
          })
          .catch(error => {
            // 请求失败处理
            console.error(error);
          });
    },
    getv(value, type) {
      if (this.endff == true && type == "num") {
        this.clearValue()
        this.resultField += value;
      } else {
        this.endff = false;
        this.resultField += value;
      }
    },
    calculate() {
      this.endff = true
      let str=this.resultField
      console.log(123)
      console.log(this.resultField, "计算")
      console.log('yes')
      if (this.resultField.indexOf('^') != -1) {
        this.resultField = this.pow(this.resultField, this.resultField.indexOf('^'))
      }
      this.resultField = eval(this.resultField);
      let calrestemp = eval(this.resultField)

      //计算存在精度问题s
      this.resultField = this.roundFun(this.resultField, 5)
      // 发送POST请求
      axios.post('/api/save', {equation:str,result:calrestemp})
          .then(response => {
            // 请求成功处理
            console.log(response.data);
          })
          .catch(error => {
            // 请求失败处理
            console.error(error);
          });
      console.log(calrestemp)
      if(calrestemp=='Infinity')
      {
        this.resultField="0不能做除数"
      }
    },

    pow(value, pos) {
      if (pos != -1) {
        let arr = value.split("")
        let powVal = Math.pow(arr[pos - 1], arr[pos + 1])
        arr.splice(pos - 1, 3, powVal)
        value = arr.join("")
        return value
      }
    },
    ans() {
      axios.get('/api/ans')
          .then(response => {
            // 请求成功处理
            console.log(response.data);
            this.resultField=response.data
          })

    },
    back() {
      this.resultField = this.resultField.substring(0, this.resultField.length - 1);
    },
    sin() {
      this.endff = true
      this.resultField = Math.sin(this.resultField);
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },

    cos() {
      this.endff = true
      this.resultField = Math.cos(this.resultField);
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },

    tan() {
      this.endff = true
      this.resultField = Math.tan(this.resultField);
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },

    sqrt() {
      this.endff = true
      this.resultField = Math.sqrt(this.resultField);
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },

    cbrt() {
      this.endff = true
      this.resultField = Math.cbrt(this.resultField)
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },

    square() {
      this.endff = true
      this.resultField = Math.pow(this.resultField, 2);
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },
    cube() {
      this.endff = true
      this.resultField = Math.pow(this.resultField, 3);
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },
    cal(value) {
      this.resultField += value;
      if (this.resultField == "NaN")
        this.resultField = "运算错误";
    },
    roundFun(value, n) {
      return Math.round(value * Math.pow(10, n)) / Math.pow(10, n);
    },
  }
}
</script>
<style scoped>
body {
  margin: 0;
  padding: 0;
  font-family: "微软雅黑", sans-serif;
  text-align: center;
}

.content {
  width: 400px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 10px;
  background-color: antiquewhite;
}

.clear,
.number,
.symbol,
.calculate {
  margin-top: 10px;
  font-size: xx-large;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  line-height: 50px;
  background-color: #FFE4C4;
  cursor: pointer;
}

.calculate {
  background-color: #888888;
  color: white;
}

.content input:hover {
  box-shadow: 5px 5px 5px #888888;
}

#result {
  margin-top: 10px;
  font-size: 16px;
  width: 60%;
  height: 50px;
  text-align: left;
  border-radius: 20px;
  background-color: #FFE4C4;
  padding: 5px 10px;
}
</style>
