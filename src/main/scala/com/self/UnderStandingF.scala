package com.self

object UnderStandingF extends App {

  /**
    * What is a value ?
    *
    * What is a proper type ?
    *
    * What is a first-order type ?
    *
    * What abstracts over a first-order type ?
    *
    * Why do I need F[_] ?
    */

  def whatIsAValue = {
    val name = "daniel"
    val one = 1
    val oneAndTwoAsTuple = (1, 2)
    val oneAndTwoInAList = List(1, 2)
  }

  /**
    * 查看REPL发出的信息。它不断告诉你关于类型：String，List[Int]等等，这些都是正确的类型
    *
    * 正确的类型比值是更高层次的概念
    *
    * types can be instantiated to produce a value
    *
    * values are a specific instance of a type
    *
    * 从"value" 到 "type" 使我们上升到一个更高层级的抽象
    */
  def whatIsAProperType = {
    val name = "daniel"
    //name: String = daniel
    val one = 1
    //one: Int = 1
    val oneAndTwoAsTuple = (1, 2)
    //oneAndTwoAsTuple: (Int, Int) = (1,2)
    val oneAndTwoInAList = List(1, 2)
    //oneAndTwoInAList: List[Int] = List(1, 2)
  }

  /**
    * First-order types are just types (List, Map, Array) that have type constructors (List[_], Map[_, _]) that take proper types and produce proper types (List[Int], Map[String, Int]).
    */
  def whatIsFirstOrderType = {
    // 这里编译器不会让我们通过，会提示我们这是某物的一个List,这个List有一个"看不见的"插槽（slot）
    // val l: List = List(1,2,3)

    //如果我们给这个看不见的slot随便放一个东西，比如 _ ,则编译通过
    val l: List[_] = List(1, 2, 3)
    //l: List[_] = List(1, 2, 3) in REPL
  }

  /**
    * 到目前为止，我们采取的每一步都在先前的抽象上添加了抽象
    * proper type 个人理解为 "恰好准确"的表达了这个type,即非常表意，make sense
    */
  def whatAbstractsOverAFirstOrderType = {
    // List("a") -> List[String] -> List[_]             -> ???
    // value     -> proper type  -> first order type -> ???

    //在Scala中，我们可以使用以下语法对一阶进行抽象
    trait WithMap[F[_]] {}

    //我们先聚焦在 F[_] 上, F[_]表示有1个slot的一阶类型，例如可能是List[_],Option[_]

    //那么问题来了，WithMap 是个啥玩意儿？答案是二阶类型：second-order type
    //这是一个抽象类型的 ！抽象类型！
  }

  // higher kinded type:a type with a type constructor 即a type with "[_]"
  // a type constructor:a function takes a type and return a type.
  def higherKindedTypes = {
    //举个例子,给定:List[_] ,List[_] 是给定"_"这个type的函数， 即takes a type: "_" return a type: List[_]
    //即：String=>List[String]
    // 所以我们可以理解为：List[_] 是个为 type:"_"服务的函数，他是type level的

    //以上，我们给定了一个具体的type(proper type),如果我给传入一个一阶类型呢？
    //我们给定一个一阶类型： List[_] ,定义一个函数： List[_]=>WithMap[List[_]],或者我们任意类推，以F[_] 代替 List[_]
    //则转变为： F[_]=> WithMap[F[_]] 这样的函数

    // 这里需要额外注意的是，higher order functions.
    // higher order functions :return a functions at the value level.
  }

  /**
    * "*" notation
    */
  def * = {
    // kind: a type of a type ,用 "*" 来表示处于什么order，也就是几阶

    //eg: String 的 kind: * , order 是 0
    //eg: List[_]的 kind: *->*, order 是1 ,例如takes a String ,return a List[String]
    //eg: Map[_,_]的kind: *->*->*, 2个 order 0 ,produce a order1
    //eg: WithMap[F[_]]: (*->*)->* ,take a order1 type: (*->*) ,return a order2 type
  }

  //我们使用F[_]这个1st order type 抽象了所有的只有1个插槽的 1st order type
  //现在我们来定义 common function between all of them.
  def whyDoINeedF = {

    trait WithMap[F[_]] {
      def map[A,B](fa:F[A])(F:A=>B) :F[B]
    }
  }

}
