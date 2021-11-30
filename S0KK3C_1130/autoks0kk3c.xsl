<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html> 
         <body> 
            <h2>autok</h2> 
            <table border = "1"> 
               <tr bgcolor = "#9acd32"> 
                  <th>rsz</th> 
                  <th>tipus</th> 
                  <th>ar</th> 
                  <th>szin</th> 
                  <th>tulaj</th>
               </tr> 
					
               <xsl:for-each select = "class/auto"> 
                  <tr> 
                     <td><xsl:value-of select = "@rsz"/></td> 
                     <td><xsl:value-of select = "rsz"/></td> 
                     <td><xsl:value-of select = "tipus"/></td> 
                     <td><xsl:value-of select = "ar"/></td> 
                     <td><xsl:value-of select = "szin"/></td>
                     <td><xsl:value-of select = "tulaj"/></td>
                  </tr> 
               </xsl:for-each> 
					
            </table> 
         </body> 
      </html> 
	</xsl:template>
</xsl:stylesheet>