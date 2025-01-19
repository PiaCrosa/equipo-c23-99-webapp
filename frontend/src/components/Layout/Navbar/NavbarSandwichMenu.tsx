import React, { useEffect, useState } from 'react';
import { routeList } from '../../../helpers/routes';
import { useLocation } from 'react-router-dom';
import { Route } from '../../../helpers/Route';
import { useNavigateCustom } from '../../../helpers/useNavigate';

interface NavbarSandwichMenuProps {
  onToggleMenu: (value: false) => void;
}

const NavbarSandwichMenu = (
  {
    onToggleMenu,
  }: NavbarSandwichMenuProps
) => {
  const location = useLocation();
  const navigateTo = useNavigateCustom();

  const [routes, setRoutes] = useState<Route[]>([]);

  useEffect(() => {
    const currentRouteRolType = routeList.find(
      route => route.path == location.pathname
    )?.routeType || 'anyone';
    const routesToShow = routeList.filter(
      route => route.routeType == currentRouteRolType && route.isShownInMenu
    );
    setRoutes(routesToShow);
  }, [location.pathname]);


  const onClickCloseMenu = () => {
    onToggleMenu(false);
  }

  return (
    <React.Fragment>
      <div
        className='
          block absolute w-full top-0 left-0 bg-white z-10 flex flex-col justify-between items-center 
          md:hidden
        '
      >
        <div
          className='
            flex flex-col items-center justify-between w-full
          '
        >

          {/* CLOSE OPTION DIV */}
          <div
            className='
              text-black w-full flex justify-end bg-sky-500
              sm:h-20
            '
          >
            <a
              className='
                block px-2 w-14 h-14 text-center content-center text-2xl text-white
                sm:w-16 sm:h-16 sm:text-3xl sm:self-center
              '
              onClick={onClickCloseMenu}
            >
              X
            </a>
          </div>

          {/* ROUTE OPTION DIV */}
          {
            routes.map(
              route => {
                return <div
                  key={route.path}
                  className='
                    border-b border-gray-400 text-black py-3 text-center
                  '
                  onClick={() => navigateTo(route.path)}
                >
                  <a
                    className='block'
                  >
                    {route.name}
                  </a>
                </div>
              }
            )
          }

        </div>
      </div>
    </React.Fragment>
  )
}

export {
  NavbarSandwichMenu,
}