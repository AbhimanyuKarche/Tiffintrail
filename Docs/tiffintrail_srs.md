# Tiffin Trail WebApp- An Online Tiffin Service

## Document
- **System Requirement Specification Document**

## Title
- **System Requirement Specification for Tiffin Trail**

## Team
- Direct Customer  
- Indirect Customer  
- Architect  
- Business Analyst  
- Quality Assurance Team  
- System Analyst  

## Objective (Purpose)
The Online tiffin service **'Tiffin Trail WebApp'** website is intended to provide a complete solution for Sellers, Customers, as well as Internal users (Staff) as a single Gateway using the internet.  
Sellers could be anyone who wants to set up their tiffin service center but doesn’t have a platform for the same, especially housewives who wish to have a source of side income.  
It will enable sellers to provide tiffin services online, and consumers to browse through all the available tiffin services and order tiffins without physically visiting the tiffin service center.

## Scope
- This System allows Customers to order tiffins on a **daily**, **weekly**, or **monthly** basis.
- Sellers will be able to maintain their menu by **adding or removing tiffins** and confirm or reject orders based on availability.
- The System will show **live Business Operation statistics** and trends through a **Customized Dashboard** for stakeholders.

## Functional Requirements

### Anonymous User
Any anonymous user will be able to:
- View different tiffin plans and tiffins available in the menu.

### Customer
Customer will be able to:
- Register and login to the website.
- View different tiffin plans and available tiffins.
- Place or cancel an order within 5 minutes.
- Pay for the order.
- See history of orders placed, filtered by time range (yearly, monthly, weekly, daily).
- Provide review/feedback for placed orders.

### Seller
Seller will be able to:
- Register and login to the website.
- Add, remove, or update tiffin details and plans.
- Confirm or reject orders based on availability.
- View tiffins sold filtered by time range (yearly, monthly, weekly, daily).
- View customer reviews/feedback.

### Internal Staff (Admin)
Admin will be able to:
- Login to the website.
- Approve seller registration.
- Approve seller requests to update the menu.
- Monitor all seller and customer activities.
- Monitor all payment transactions.
- Resolve disputes between sellers and customers.
- Make payments to sellers based on sales.

## Non-functional Requirements

###  Security
- Only registered customers can place orders.
- Role-based access control will be used for all stakeholders.
- SSL encryption for secure communication.
- Session timeout for inactive users.
- Encrypted data transmission between web, app, and DB servers.
- Firewall protection and phishing prevention.

###  Reliability
- Regular backup and quick recovery of business data.
- Load balancing during peak hours for consistent performance.
- Continuous monitoring and system updates.

###  Maintainability
- Use of commercial database software.
- Hosted on a reliable web server.
- Admin tools for easy monitoring and configuration.
- Separate environments for development, testing, and production.

###  Portability
- Responsive web design (HTML, CSS, JS) using **Tailwind CSS** for consistent UI/UX across devices.
- Can be deployed on single/multi-server setups or cloud platforms (Azure, AWS, GCP).

###  Availability
- **99.999% uptime**, available 24x7.

###  Accessibility
- Registered users can place orders.
- Sellers can manage menus and orders.
- Admins can access dashboards showing business performance.

###  Durability
- Order cart retained for 15 minutes during disconnection.
- Backup and recovery for all user and business data.

###  Efficiency
- Maintains response time during peak/festival seasons.
- Handles concurrent transactions with data isolation.

###  Modularity
- Built with loosely coupled, cohesive modules.
- Modules include: login, registration, menu, cart, order processing, payment, membership, role management.

###  Scalability
- Consistent user experience under varying loads.

###  Safety
- Firewall and antivirus protection.
- Incremental backup strategy.
- Role-based security for data and operations.