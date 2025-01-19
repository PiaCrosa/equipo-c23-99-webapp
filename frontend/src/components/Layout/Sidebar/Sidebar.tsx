import React from 'react'

const Sidebar = () => {



  return (
    <React.Fragment>
      <aside
        className='
            hidden
            md:flex md:flex-col md:justify-between md:bg-sky-500 md:sticky md:self-start md:h-[calc(100vh-5rem)]
          '
      >
        {/* FIRST OPTIONS */}
        <div>
          <div
            className='
              md:py-3 md:px-6 md:text-white
            '
          >
            Home
          </div>
          <div
            className='
              md:py-3 md:px-6 md:text-white
            '
          >
            Administrar usuarios
          </div>
          <div
            className='
              md:py-3 md:px-6 md:text-white
            '
          >
            Administrar inventario
          </div>
        </div>

        {/* LAST OPTIONS */}
        <div>
          <div
            className='
              md:py-3 md:px-6 md:text-white
            '
          >
            Mi Perfil
          </div>
          <div
            className='
              md:py-3 md:px-6 md:text-white
            '
          >
            Terminos y Condiciones
          </div>
        </div>
      </aside>
    </React.Fragment>
  )
}

export {
  Sidebar,
}