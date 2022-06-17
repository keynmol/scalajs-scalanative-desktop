package webview

import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scalanative.libc.*

object types:
  opaque type webview_t = Ptr[Byte]
  object webview_t:
    given _tag: Tag[webview_t] = Tag.Ptr(Tag.Byte)
    inline def apply(inline o: Ptr[Byte]): webview_t = o

@extern
private[webview] object extern_functions:
  import types.*
  def webview_bind(w : webview_t, name : CString, fn : CFuncPtr3[CString, CString, Ptr[Byte], Unit], arg : Ptr[Byte]): Unit = extern

  def webview_create(debug : CInt, window : Ptr[Byte]): webview_t = extern

  def webview_destroy(w : webview_t): Unit = extern

  def webview_dispatch(w : webview_t, fn : CFuncPtr2[webview_t, Ptr[Byte], Unit], arg : Ptr[Byte]): Unit = extern

  def webview_eval(w : webview_t, js : CString): Unit = extern

  def webview_get_window(w : webview_t): Ptr[Byte] = extern

  def webview_init(w : webview_t, js : CString): Unit = extern

  def webview_navigate(w : webview_t, url : CString): Unit = extern

  def webview_return(w : webview_t, seq : CString, status : CInt, result : CString): Unit = extern

  def webview_run(w : webview_t): Unit = extern

  def webview_set_html(w : webview_t, html : CString): Unit = extern

  def webview_set_size(w : webview_t, width : CInt, height : CInt, hints : CInt): Unit = extern

  def webview_set_title(w : webview_t, title : CString): Unit = extern

  def webview_terminate(w : webview_t): Unit = extern

  def webview_unbind(w : webview_t, name : CString): Unit = extern

object functions:
  import types.*
  import extern_functions.*
  export extern_functions.*
