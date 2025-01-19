// useNavigateCustom.ts
import { useNavigate } from 'react-router-dom';

const useNavigateCustom = () => {
  const navigate = useNavigate();
  const navigateTo = (path: string) => {
    navigate(path);
  };
  return navigateTo;
};

export {
  useNavigateCustom,
};
