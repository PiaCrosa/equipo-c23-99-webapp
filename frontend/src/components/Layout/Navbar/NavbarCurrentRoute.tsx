import React from 'react'
import { useGetCurrentRoute } from '../../../helpers/useGetCurrentRoute';

const NavbarCurrentRoute = () => {
  const currentRoute = useGetCurrentRoute();

  return (
    <React.Fragment>
      <div
        className='
          hidden
          md:block md:content-center md:text-xl
        '
      >
        {currentRoute?.name}
      </div>
    </React.Fragment>
  )
}

export {
  NavbarCurrentRoute,
}