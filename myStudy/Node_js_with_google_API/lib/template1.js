module.exports = {
    HTML:function(data, sql)
    {
      return `
      <!DOCTYPE html>
      <html>
        <head>
        </head>
        <body>
        ${data}
        ${sql}
        </body>
      </html>
      `
    }
}

                    