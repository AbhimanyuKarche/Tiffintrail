import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Cart from "../Components/Cart";

export default function SellersPage() {
  const navigate = useNavigate();

  const [sellers, setSellers] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // UI states
  const [query, setQuery] = useState("");
  const [selectedSeller, setSelectedSeller] = useState(null);
  const [tiffins, setTiffins] = useState([]);
  const [tiffinLoading, setTiffinLoading] = useState(false);
  const [showModal, setShowModal] = useState(false);

  // Pagination (simple)
  const [page, setPage] = useState(1);
  const perPage = 12;

  // helper to get auth headers if user is logged in
  const getAuthHeaders = () => {
    const token = localStorage.getItem("token");
    return token ? { Authorization: `Bearer ${token}` } : {};
  };

  useEffect(() => {
    let cancelled = false;
    const load = async () => {
      setLoading(true);
      setError(null);
      try {
        const res = await axios.get(
          "http://localhost:8080/api/seller/approved",
          {
            headers: getAuthHeaders(),
          }
        );
        if (!cancelled) {
          setSellers(res.data || []);
          setFiltered(res.data || []);
        }
      } catch (err) {
        if (!cancelled) setError("Failed to load sellers. Try again.");
      } finally {
        if (!cancelled) setLoading(false);
      }
    };
    load();
    return () => (cancelled = true);
  }, []);

  // search + filter
  useEffect(() => {
    const q = query.trim().toLowerCase();
    if (!q) {
      setFiltered(sellers);
      setPage(1);
      return;
    }
    const out = sellers.filter((s) => {
      const shop = String(s.businessName || "").toLowerCase();
      const desc = String(s.description || "").toLowerCase();
      const name = String(s.user?.fullName || "").toLowerCase();
      const loc = String(
        `${s.address || ""} ${s.city || ""} ${s.pincode || ""}`
      ).toLowerCase();
      return (
        shop.includes(q) ||
        desc.includes(q) ||
        name.includes(q) ||
        loc.includes(q)
      );
    });
    setFiltered(out);
    setPage(1);
  }, [query, sellers]);

  // paginate
  const paginated = () => {
    const start = (page - 1) * perPage;
    return filtered.slice(start, start + perPage);
  };

  // open seller view -> fetch their tiffins
  const openSeller = async (seller) => {
    setSelectedSeller(seller);
    setShowModal(true);
    setTiffins([]);
    setTiffinLoading(true);
    try {
      const res = await axios.get(
        `http://localhost:8080/api/tiffins/seller/${seller.id}`,
        { headers: getAuthHeaders() }
      );
      setTiffins(res.data || []);
    } catch (err) {
      setTiffins([]);
    } finally {
      setTiffinLoading(false);
    }
  };

  const closeModal = () => {
    setShowModal(false);
    setSelectedSeller(null);
    setTiffins([]);
  };

  const goToSellerPage = (seller) => {
    navigate(`/seller/${seller.id}`);
  };

  return (
    <div className="min-h-screen bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="mx-auto max-w-7xl">
        {/* header */}
        <div className="mb-8 flex flex-col md:flex-row md:items-center md:justify-between gap-4">
          <div>
            <h1 className="text-3xl font-extrabold text-gray-900">
              Order from your favourite Seller
            </h1>
            <p className="mt-1 text-sm text-gray-600">
              Browse approved local tiffin sellers and order fresh meals in
              minutes.
            </p>
          </div>

          <div className="flex flex-col sm:flex-row items-stretch gap-2">
            <div className="relative">
              <input
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Search by shop, name, location or dish..."
                className="w-full sm:w-80 rounded-md border border-gray-300 px-4 py-2 pr-10 focus:outline-none focus:ring-2 focus:ring-indigo-500"
              />
              <div className="absolute right-2 top-2 text-gray-400">🔎</div>
            </div>

            <button
              onClick={() => {
                setQuery("");
              }}
              className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-medium text-gray-700 shadow-sm ring-1 ring-inset ring-gray-200 hover:bg-gray-50"
            >
              Reset
            </button>
          </div>
        </div>
        <Cart />

        {/* content */}
        <div className="bg-white shadow sm:rounded-lg p-6">
          {loading ? (
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
              {Array.from({ length: 8 }).map((_, i) => (
                <div
                  key={i}
                  className="animate-pulse rounded-lg border border-gray-200 p-4"
                >
                  <div className="h-40 w-full rounded-md bg-gray-200" />
                  <div className="mt-4 h-4 w-3/4 rounded bg-gray-200" />
                  <div className="mt-2 h-3 w-1/2 rounded bg-gray-200" />
                </div>
              ))}
            </div>
          ) : error ? (
            <div className="text-center py-8">
              <p className="text-red-600">{error}</p>
              <button
                onClick={() => window.location.reload()}
                className="mt-4 inline-flex items-center px-4 py-2 rounded bg-indigo-600 text-white"
              >
                Retry
              </button>
            </div>
          ) : filtered.length === 0 ? (
            <div className="text-center py-12">
              <p className="text-gray-600">
                No sellers found. Try changing your search or clearing filters.
              </p>
            </div>
          ) : (
            <>
              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                {paginated().map((seller) => (
                  <article
                    key={seller.id}
                    className="rounded-xl border border-gray-100 bg-white p-4 shadow-sm"
                  >
                    <div className="relative h-44 w-full overflow-hidden rounded-md bg-gradient-to-br from-indigo-50 to-white">
                      {/* placeholder image / initials */}
                      <div className="absolute left-4 top-4 rounded-full bg-white/60 px-3 py-1 text-xs font-medium text-indigo-700">
                        New
                      </div>
                      <div className="absolute left-4 bottom-4 text-lg font-semibold text-gray-900">
                        {seller.businessName || "Unnamed Shop"}
                      </div>
                    </div>

                    <div className="mt-4">
                      <div className="flex items-center justify-between">
                        <div>
                          <h3 className="text-sm font-semibold text-gray-900">
                            {seller.user?.fullName || "Seller"}
                          </h3>
                          <p className="mt-1 text-xs text-gray-500">
                            {seller.city || "Location not specified"}
                          </p>
                        </div>
                        <div className="ml-3 flex items-center gap-2">
                          <button
                            onClick={() => openSeller(seller)}
                            className="inline-flex items-center rounded-md bg-indigo-600 px-3 py-1 text-xs font-medium text-white hover:bg-indigo-700"
                          >
                            View Menu
                          </button>

                          <button
                            onClick={() => goToSellerPage(seller)}
                            className="inline-flex items-center rounded-md border border-indigo-600 px-3 py-1 text-xs font-medium text-indigo-600 hover:bg-indigo-50"
                          >
                            Order
                          </button>
                        </div>
                      </div>

                      <p className="mt-3 text-sm text-gray-600 line-clamp-3">
                        {seller.description || "No description available."}
                      </p>

                      <div className="mt-4 flex items-center justify-between">
                        <div className="flex items-center gap-3">
                          <span className="text-xs text-gray-500">
                            {seller.city}, {seller.pincode}
                          </span>
                          <span className="text-xs text-gray-500">•</span>
                          <span className="text-xs text-gray-500">
                            Pickup only
                          </span>
                        </div>

                        <div>
                          <button
                            title="Favourite"
                            className="rounded-full p-2 hover:bg-gray-100"
                          >
                            ❤️
                          </button>
                        </div>
                      </div>
                    </div>
                  </article>
                ))}
              </div>

              {/* pagination controls */}
              <div className="mt-8 flex items-center justify-between">
                <div className="text-sm text-gray-600">
                  Showing {(page - 1) * perPage + 1} —{" "}
                  {Math.min(page * perPage, filtered.length)} of{" "}
                  {filtered.length}
                </div>
                <div className="flex items-center gap-2">
                  <button
                    onClick={() => setPage((p) => Math.max(1, p - 1))}
                    disabled={page === 1}
                    className="inline-flex items-center rounded-md border px-3 py-1 text-sm disabled:opacity-50"
                  >
                    Prev
                  </button>
                  <button
                    onClick={() =>
                      setPage((p) =>
                        p * perPage < filtered.length ? p + 1 : p
                      )
                    }
                    disabled={page * perPage >= filtered.length}
                    className="inline-flex items-center rounded-md border px-3 py-1 text-sm disabled:opacity-50"
                  >
                    Next
                  </button>
                </div>
              </div>
            </>
          )}
        </div>
      </div>

      {/* Seller modal */}
      {showModal && selectedSeller && (
        <div className="fixed inset-0 z-50 flex items-center justify-center">
          <div className="absolute inset-0 bg-black/40" onClick={closeModal} />

          <div className="relative z-10 w-[min(90%,1000px)] rounded-lg bg-white p-6 shadow-lg">
            <div className="flex items-start justify-between">
              <div>
                <h2 className="text-xl font-semibold">
                  {selectedSeller.businessName || "Shop"}
                </h2>
                <p className="text-sm text-gray-500">
                  {selectedSeller.user?.fullName} •{" "}
                  {selectedSeller.city || "Location not specified"}
                </p>
              </div>
              <div className="flex items-center gap-2">
                <button
                  onClick={() => goToSellerPage(selectedSeller)}
                  className="inline-flex items-center rounded-md bg-indigo-600 px-3 py-1 text-sm font-medium text-white"
                >
                  Order from this seller
                </button>
                <button
                  onClick={closeModal}
                  className="rounded-md px-3 py-1 text-sm"
                >
                  Close
                </button>
              </div>
            </div>

            <div className="mt-6">
              {tiffinLoading ? (
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  {Array.from({ length: 4 }).map((_, i) => (
                    <div key={i} className="rounded-md border p-4">
                      <div className="h-28 w-full rounded bg-gray-100" />
                      <div className="mt-2 h-4 w-3/4 rounded bg-gray-100" />
                    </div>
                  ))}
                </div>
              ) : tiffins.length === 0 ? (
                <div className="text-center text-gray-600 py-8">
                  No tiffins available from this seller.
                </div>
              ) : (
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  {tiffins.map((t) => (
                    <div key={t.id} className="rounded-md border p-4">
                      <div className="flex items-start gap-4">
                        <div className="h-20 w-20 flex-shrink-0 overflow-hidden rounded-md bg-gray-100">
                          <img
                            src={t.imageUrl}
                            alt={t.title}
                            className="h-full w-full object-cover"
                          />
                        </div>

                        <div className="flex-1">
                          <h3 className="text-sm font-semibold">{t.title}</h3>
                          <p className="mt-1 text-xs text-gray-500 line-clamp-2">
                            {t.description}
                          </p>

                          <div className="mt-3 flex items-center justify-between">
                            <div className="text-sm font-medium">
                              ₹ {t.price}
                            </div>
                            <div className="flex items-center gap-2">
                              <button
                                onClick={() => navigate(`/tiffin/${t.id}`)}
                                className="rounded-md border px-3 py-1 text-sm"
                              >
                                View
                              </button>
                              <button
                                onClick={() =>
                                  navigate(`/tiffin/${t.id}/order`)
                                }
                                className="rounded-md bg-indigo-600 px-3 py-1 text-sm text-white"
                              >
                                Add to cart
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
