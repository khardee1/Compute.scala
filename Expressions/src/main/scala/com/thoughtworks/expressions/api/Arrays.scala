package com.thoughtworks.expressions.api

import com.thoughtworks.expressions.Anonymous.Implicitly
import com.thoughtworks.feature.Factory.inject

/**
  * @author 杨博 (Yang Bo)
  */
trait Arrays extends Values {
  type Category >: this.type <: Arrays

  protected trait ValueTermApi extends super.ValueTermApi { thisValue: ValueTerm =>
    def fill(shape: Int*): ArrayTerm {
      type Element = thisValue.ThisTerm
    }
  }

  override type ValueTerm <: (Term with Any) with ValueTermApi

  protected trait ArrayTermApi extends TermApi { thisArray: ArrayTerm =>
    type Element <: ValueTerm

    val shape: Seq[Int]

    def extract: Element

  }

  type ArrayTerm <: (Term with Any) with ArrayTermApi

  @inject
  val array: Implicitly[ArrayCompanion]

  protected trait ArrayCompanionApi {

    def parameter[ElementType <: ValueType](id: Any, elementType: ElementType, shape: Int*): ArrayTerm {
      type Element = elementType.ThisTerm
    }

  }

  type ArrayCompanion <: ArrayCompanionApi

}
