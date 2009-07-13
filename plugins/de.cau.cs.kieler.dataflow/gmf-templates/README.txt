@author Hauke Fuhrmann, <haf@informatik.uni-kiel.de>
@date 2008-06-17

This is a template folder for GMF templates to alter the standard GMF code generation.

Put a copy (same filename AND directory structure) of the Template to alter into the "aspects"
folder. Change the implementation of any "DEFINE" statements (you can only provide some or one 
of the DEFINES, you can omit the rest).

Open the .gmfgen model and set the "Dynamic Templates" property to true and
specify the "Template Directory" there as "/<plugin name>/<template folder>", e.g.
"/de.cau.cs.kieler.dataflow/gmf-templates"