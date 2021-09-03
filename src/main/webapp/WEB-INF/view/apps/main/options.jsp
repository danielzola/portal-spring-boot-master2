<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mytab theme1">
      <ul class="nav nav-tabs">
            <li> <a class="nav-link active" data-toggle="tab" data-target="#tab1"> General</a> </li>
            <li> <a class="nav-link" data-toggle="tab" data-target="#tab2">Sideheader</a> </li>
      </ul><!-- Selectors -->			
      <div class="tab-content">
            <div class="tab-pane fade show active" id="tab1">
                  <form>
                       <div class="console-form-body">
                             <h6 class="subtitle">Header Settings</h6>
                             <div class="row">
                                   <div class="col-lg-12 col-md-12">
                                         <div class="form-group">
                                               <label>Header Background</label>
                                               <div class="console-radiobuttons-list" id="header_bg">
                                                     <label class="con-radio"> <input id="default_header" type="radio" name="header_bg"> Default <span class="radiomark"></span> </label>
                                                     <label class="con-radio"> <input id="coloured_header" type="radio"  name="header_bg"> Coloured <span class="radiomark"></span> </label>
                                                     <label class="con-radio"> <input id="gradient_header" type="radio" name="header_bg"> Gradient <span class="radiomark"></span> </label>
                                                     <label class="con-radio"> <input id="dark_header" type="radio" name="header_bg"> Dark<span class="radiomark"></span> </label>
                                               </div>
                                         </div>
                                   </div>
                             </div>

					<div class="con-separator con-separator--border-dashed con-separator--space-md"></div>
                              <h6 class="subtitle">Widgets Settings</h6>
                              <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                          <div class="form-group row align-items-center">
                                                <label class="col-6 col-form-label">Draggable Widgets</label>
                                                <div class="col-6 flex">
                                                      <span class="console-switch ml-auto">
                                                            <label class="switch">
                                                                  <input id="draggable_widgets" type="checkbox" checked='checked'>
                                                                  <span class="slider round"></span>
                                                            </label>
                                                      </span>
                                                </div>
                                          </div>
                                    </div>

                                    <div class="col-lg-12 col-md-12">
                                          <div class="form-group row align-items-center">
                                                <label class="col-6 col-form-label">Resizable Widgets</label>
                                                <div class="col-6 flex">
                                                      <span class="console-switch ml-auto">
                                                            <label class="switch">
                                                                  <input id="resizable_widget" type="checkbox" checked='checked'>
                                                                  <span class="slider round"></span>
                                                            </label>
                                                      </span>
                                                </div>
                                          </div>
                                    </div>
                              </div>
                              <div class="con-separator con-separator--border-dashed con-separator--space-md"></div>
                        </div>
                  </form>
            </div>
            <div class="tab-pane fade" id="tab2">
                  <form >
                        <div class="console-form-body">
                              <div class="row">
                                    <div class="col-lg-12 col-md-12">
                                          <div class="form-group row align-items-center">
                                                <label class="col-6 col-form-label">Select Style</label>
                                                <div class="col-6 flex">
                                                      <select id="sideheader_settings" class="select2 classic form-control">
                                                            <option value="default">Default</option>
                                                            <option value="top">Top</option>
                                                            <option value="contained">Contained</option>
                                                            <option value="narrow">Narrow</option>
                                                      </select>
                                                </div>
                                          </div>
                                    </div>

                                    <div class="col-lg-12 col-md-12">
                                          <div class="form-group">
                                                <label>Sidemenu Background</label>
                                                <div class="console-radiobuttons-list" id="sidemenu_bg">
                                                      <label class="con-radio"> <input id="default_sidemenu" type="radio" name="sidemenu_bg"> Default <span class="radiomark"></span> </label>
                                                      <label class="con-radio"> <input id="coloured_sidemenu" type="radio"  name="sidemenu_bg"> Coloured <span class="radiomark"></span> </label>
                                                      <label class="con-radio"> <input id="gradient_sidemenu" type="radio" name="sidemenu_bg"> Gradient <span class="radiomark"></span> </label>
                                                      <label class="con-radio"> <input id="dark_sidemenu" type="radio" name="sidemenu_bg"> Dark<span class="radiomark"></span> </label>
                                                      <label class="con-radio"> <input id="dark2_sidemenu" type="radio" name="sidemenu_bg"> Dark 2<span class="radiomark"></span> </label>
                                                </div>
                                          </div>
                                    </div>

                              </div>
                        </div>
                  </form>
            </div>
      </div>
</div>