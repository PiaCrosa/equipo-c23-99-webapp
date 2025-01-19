import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { routeList } from './routes';
import { Route } from './Route';

const useGetCurrentRoute = () => {
  const location = useLocation();
  const [
    currentRoute,
    setCurrentRoute,
  ] = useState<Route>();

  useEffect(() => {
    setCurrentRoute(
      routeList.find(route => route.path == location.pathname)
    );
  }, [location.pathname]);

  return currentRoute;
}

export {
  useGetCurrentRoute,
}