import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { Route } from './Route';
import { routeList } from './routes';
import { RouteType } from './RolesType';

interface UseGetMenuRoutesProps {
  menuType: RouteType,
}

const useGetMenuRoutes = ({ menuType }: UseGetMenuRoutesProps) => {
  const location = useLocation();
  const [routes, setRoutes] = useState<Route[]>([]);

  useEffect(() => {
    const routesToShow = routeList.filter(
      route => route.routeType == menuType && route.isShownInMenu
    );
    setRoutes(routesToShow);
  }, [location.pathname, menuType]);

  return routes;
}

export {
  useGetMenuRoutes,
}
