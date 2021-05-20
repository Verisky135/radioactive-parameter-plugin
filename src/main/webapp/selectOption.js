var radioactiveParams = document.querySelectorAll("select[name='selectedOption']");
var i;
for (i = 0; i < radioactiveParams.length; i++) {
    radioactiveParams[i].setAttribute("onchange", "onSelectOption(this)");
} 

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("select[name='selectedOption']").onchange();
});
  
function onSelectOption(self){
    var value = self.value;
    var selfId = self.parentNode.querySelector("input[name='name']").value;
    
    // Get enabled parameters list
    var enabledParameters = document.querySelector("[title='enabledParameters:" + value + ":" + selfId + "']")
            .getAttribute("value").split(",");
    var enabledParametersSet = new Set(enabledParameters);
    var hideMode = document.querySelector("input[name='hideMode:" + selfId + "']").getAttribute("value");
    
    console.log("Option selected: " + value +  ", enabled parameters: " + enabledParameters);
    
    
    // Loop through all existing parameters
    var allParameters = document.querySelectorAll("div[class~='setting-name']");
    for (i = 0; i < allParameters.length; i++) {
        var parameter = allParameters[i];
        var elem = parameter.parentNode
            .querySelector("div[class='setting-main']")
            .querySelector("div[name='parameter']")
            .querySelector("input[name='name']");
        var isRadioactiveParam = parameter.parentNode
            .querySelector("div[class='setting-main']")
            .querySelector("div[name='parameter']")
            .querySelector("script[src='/plugin/radioactive-parameter-plugin/selectOption.js']") !== null ;
        var id = elem !== null ? elem.value : "";
        if (selfId !== id) {
            if (enabledParametersSet.has(parameter.textContent)) {
                
                // Show elements
                if (hideMode === "Hide elements") {
                    parameter.parentNode.style.display = "table-row-group";
                    if (isRadioactiveParam) {
                        var childElem = parameter.parentNode
                            .querySelector("div[class='setting-main']")
                            .querySelector("div[name='parameter']")
                            .querySelector("select[name='selectedOption']");
                        
                        if (self.getAttribute("radioactiveParamParent") != id) {
                            childElem.setAttribute("radioactiveParamParent", selfId); 
                            childElem.onchange();
                        }
                    }
                }
                else {
                    var parameterElement = parameter.parentNode.querySelector("div[class='setting-main']").querySelector("div[name='parameter']").lastChild;

                    parameterElement.style.backgroundColor = "white";
                    parameterElement.style.userSelect = "auto";
                    parameterElement.readOnly = false;
                    
                }
            }
            else {
                
                // Hide or disable elements
                if (hideMode === "Hide elements") {
                    if (!isRadioactiveParam || self.getAttribute("radioactiveParamParent") != id) {
                        parameter.parentNode.style.display = "none";
                    }
                }
                else {
                    var parameterElement = parameter.parentNode.querySelector("div[class='setting-main']").querySelector("div[name='parameter']").lastChild;
                    parameterElement.style.backgroundColor = "#DDD";
                    parameterElement.style.userSelect = "none";
                    parameterElement.readOnly = true;

                }
            }
        }
        else {
            // Prevent to hide self
        }
    }
    
}